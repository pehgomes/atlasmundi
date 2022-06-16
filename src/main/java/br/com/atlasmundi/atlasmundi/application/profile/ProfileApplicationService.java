package br.com.atlasmundi.atlasmundi.application.profile;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.application.exception.*;
import br.com.atlasmundi.atlasmundi.application.util.Util;
import br.com.atlasmundi.atlasmundi.domain.*;
import br.com.atlasmundi.atlasmundi.domain.enumeration.InviteStatus;
import br.com.atlasmundi.atlasmundi.domain.helper.LocationHelper;
import br.com.atlasmundi.atlasmundi.domain.repository.FriendRepository;
import br.com.atlasmundi.atlasmundi.domain.repository.InviteRepository;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import br.com.atlasmundi.atlasmundi.port.adapter.web.ProfileData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Component
public class ProfileApplicationService {

    private final ProfileRepository profileRepository;
    private final InviteRepository inviteRepository;
    private final FriendRepository friendRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileApplicationService(ProfileRepository profileRepository,
                                     InviteRepository inviteRepository,
                                     FriendRepository friendRepository,
                                     PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.inviteRepository = inviteRepository;
        this.friendRepository = friendRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileId createAccount(ProfileCommand command) {

        if (!Util.isValidTaxId(command.getTaxId()))
            throw new BusinessLogicException(new InvalidTaxId(command.getTaxId()));

        this.validUniqueResultsProfile(command);

        var profile = new Profile(profileRepository.nextId(),
                command.getLogin(),
                passwordEncoder.encode(command.getPassword()),
                command.getTaxId(),
                command.getName(),
                command.getBirthDate(),
                command.getPhoneNumber(),
                command.getEmail(), command.getLatitude(), command.getLongitude());

        profileRepository.save(profile);

        return profile.getProfileId();
    }

    public ProfileData getProfileDetail(ProfileId profileId) {

        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new BusinessLogicException(new ProfileNotFound(profileId.toString())));

        var friends = profileRepository.findFriendsFromProfile(profile.getProfileId());

        return new ProfileData(profile.getProfileId(),
                profile.getLogin(),
                profile.getTaxId(),
                profile.getUsername(),
                profile.getBirthDate(),
                profile.getPhoneNumber(),

                friends.stream()
                        .map(friend -> new ProfileData.FriendData(
                                new FriendId(friend.getProfileId().uuid().toString()),
                                friend.getUsername(),
                                LocationHelper.getDistanceBetween(profile.getLatitude(), profile.getLongitude(),
                                        friend.getLatitude(), friend.getLongitude()).doubleValue() < 3,
                                LocationHelper.getDistanceBetween(profile.getLatitude(), profile.getLongitude(),
                                        friend.getLatitude(), friend.getLongitude()) + " km de distancia voce"
                        )).collect(Collectors.toList()),

                profile.getInvites().stream()
                        .filter(invite -> invite.getStatus().equals(InviteStatus.PENDING))
                        .map(invite -> new ProfileData.InviteData(
                                invite.getInviteId(),
                                invite.getRequester().getUsername(),
                                invite.getStatus()))
                        .collect(Collectors.toList()));
    }

    public InviteId deferInvite(ProfileId profileId, InviteId inviteId, InviteStatus status) {

        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new BusinessLogicException(new ProfileNotFound(profileId.toString())));

        var invite = inviteRepository.findByIdAndRequester(inviteId, profile)
                .orElseThrow(() -> new BusinessLogicException(new InviteNotFoundForCustomer(profileId.toString())));

        if (invite.getStatus().equals(InviteStatus.PENDING) && status.equals(InviteStatus.ACCEPTED)) {

            friendRepository.save(new Friend(
                    friendRepository.nextIdentity(),
                    invite.getRequester(),
                    invite.getReceiver(),
                    OffsetDateTime.now(),
                    InviteStatus.ACCEPTED));

            invite.accept();
        } else if (invite.getStatus().equals(InviteStatus.PENDING) && status.equals(InviteStatus.REFUSED)) {
            invite.refuse();
        }

        inviteRepository.save(invite);

        return invite.getInviteId();
    }

    private void validUniqueResultsProfile(ProfileCommand command) {

        var login = profileRepository.findByLogin(command.getLogin());

        var taxId = profileRepository.findByTaxId(command.getTaxId());

        var email = profileRepository.findByEmail(command.getEmail());

        var phoneNumber = profileRepository.findByPhoneNumber(command.getPhoneNumber());

        if (login.isPresent())
            throw new BusinessLogicException(new LoginAlreadyInUse(command.getLogin()));

        if (taxId.isPresent())
            throw new BusinessLogicException(new TaxIdAlreadyExists(command.getTaxId()));

        if (email.isPresent())
            throw new BusinessLogicException(new EmailAlreadyInUse(command.getEmail()));

        if (phoneNumber.isPresent())
            throw new BusinessLogicException(new PhoneNumberAlreadyInUse(command.getPhoneNumber()));
    }

    public void setLastLocation(ProfileId profileId, ProfileCommand.ProfileCurrentLocation command) {

        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new BusinessLogicException(new ProfileNotFound(profileId.toString())));

        profile.setLatitude(command.getLatitude());
        profile.setLongitude(command.getLongitude());

        profileRepository.save(profile);
    }

    public void updateProfile(ProfileId profileId, ProfileCommand command) {
        this.validUniqueResultsProfile(command);

        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new BusinessLogicException(new ProfileNotFound(profileId.toString())));

        profile.setBirthDate(command.getBirthDate());
        profile.setEmail(command.getEmail());
        profile.setLatitude(command.getLatitude());
        profile.setLongitude(command.getLongitude());
        profile.setLogin(command.getLogin());
        profile.setPhoneNumber(command.getPhoneNumber());
        profile.setTaxId(command.getTaxId());
        profile.setPassword(command.getPassword());
        profile.setUsername(command.getName());

        profileRepository.save(profile);
    }
}
