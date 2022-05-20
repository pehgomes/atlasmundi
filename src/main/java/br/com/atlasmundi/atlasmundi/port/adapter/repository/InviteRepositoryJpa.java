package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Invite;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.repository.InviteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class InviteRepositoryJpa implements InviteRepository {

    private InviteRepositorySpringData repositorySpringData;

    public InviteRepositoryJpa(InviteRepositorySpringData repositorySpringData) {
        this.repositorySpringData = repositorySpringData;
    }

    @Override
    public Invite save(Invite invite) {
        return repositorySpringData.save(invite);
    }

    @Override
    public Optional<Invite> findByIdAndReceiver(InviteId inviteId, Profile receiver) {
        return repositorySpringData.findByInviteIdAndReceiver(inviteId, receiver);
    }

    @Override
    public Optional<Invite> findByIdAndRequester(InviteId inviteId, Profile requester) {
        return repositorySpringData.findByInviteIdAndRequester(inviteId, requester);
    }
}
