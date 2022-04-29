package br.com.atlasmundi.atlasmundi.application.profile;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.application.exception.BusinessLogicException;
import br.com.atlasmundi.atlasmundi.application.exception.InvalidTaxId;
import br.com.atlasmundi.atlasmundi.application.exception.TaxIdAlreadyExists;
import br.com.atlasmundi.atlasmundi.application.util.Util;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import br.com.atlasmundi.atlasmundi.port.adapter.web.ProfileData;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProfileApplicationService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileApplicationService(ProfileRepository profileRepository,
                                     PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileId createAccount(ProfileCommand command) {

        if (!Util.isValidTaxId(command.getTaxId()))
            throw new BusinessLogicException(new InvalidTaxId(command.getTaxId()));

        var profileOption = profileRepository.findByTaxId(command.getTaxId());

        if (profileOption.isPresent())
            throw new BusinessLogicException(new TaxIdAlreadyExists(command.getTaxId()));

        var profile = new Profile(profileRepository.nextId(),
                command.getLogin(),
                passwordEncoder.encode(command.getPassword()),
                command.getTaxId(),
                command.getName(),
                command.getBirthDate(),
                command.getPhoneNumber(),
                command.getEmail());

        profileRepository.save(profile);

        return profile.getProfileId();
    }

    public ProfileData getProfileDetail(ProfileId profileId) {

        var profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return new ProfileData(profile.getProfileId(),
                profile.getLogin(),
                profile.getTaxId(),
                profile.getUsername(),
                profile.getBirthDate(),
                profile.getPhoneNumber(), new ArrayList<>(), new ArrayList<>());
    }
}
