package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Friend;
import br.com.atlasmundi.atlasmundi.domain.Invite;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.repository.FriendRepository;
import br.com.atlasmundi.atlasmundi.domain.repository.InviteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class FriendRepositoryJpa implements FriendRepository {

    private FriendRepositorySpringData repositorySpringData;

    public FriendRepositoryJpa(FriendRepositorySpringData repositorySpringData) {
        this.repositorySpringData = repositorySpringData;
    }

    @Override
    public Friend save(Friend invite) {
        return repositorySpringData.save(invite);
    }

}
