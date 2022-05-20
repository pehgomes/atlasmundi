package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.*;
import br.com.atlasmundi.atlasmundi.domain.repository.FriendRepository;
import br.com.atlasmundi.atlasmundi.domain.repository.InviteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Override
    public List<Friend> findFriendsByProfile(ProfileId profileId) {
        return repositorySpringData.findByRequesterOrReceiver(profileId.uuid());
    }

}
