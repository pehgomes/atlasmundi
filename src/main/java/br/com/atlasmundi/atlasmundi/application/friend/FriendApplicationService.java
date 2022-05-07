package br.com.atlasmundi.atlasmundi.application.friend;

import br.com.atlasmundi.atlasmundi.application.command.FriendCommand;
import br.com.atlasmundi.atlasmundi.application.exception.BusinessLogicException;
import br.com.atlasmundi.atlasmundi.application.exception.ProfileNotFound;
import br.com.atlasmundi.atlasmundi.domain.Invite;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.enumeration.InviteStatus;
import br.com.atlasmundi.atlasmundi.domain.repository.InviteRepository;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class FriendApplicationService {

    private final ProfileRepository profileRepository;
    private final InviteRepository inviteRepository;

    public FriendApplicationService(ProfileRepository profileRepository,
                                    InviteRepository inviteRepository) {
        this.profileRepository = profileRepository;
        this.inviteRepository = inviteRepository;
    }

    public InviteId inviteAFriend(FriendCommand command) {

        var profileFrom = profileRepository.findById(command.getFrom())
                .orElseThrow(() -> new BusinessLogicException(new ProfileNotFound(command.getFrom().toString())));

        var profileTo = profileRepository.findById(command.getTo())
                .orElseThrow(() -> new BusinessLogicException(new ProfileNotFound(command.getTo().toString())));

        var invite = inviteRepository.save(
                new Invite(inviteRepository.nextIdentity(),
                        profileFrom,
                        profileTo,
                        OffsetDateTime.now(),
                        InviteStatus.PENDING));

        return invite.getInviteId();
    }

}
