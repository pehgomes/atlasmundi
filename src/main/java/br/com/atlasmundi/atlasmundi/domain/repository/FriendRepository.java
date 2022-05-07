package br.com.atlasmundi.atlasmundi.domain.repository;

import br.com.atlasmundi.atlasmundi.domain.Friend;
import br.com.atlasmundi.atlasmundi.domain.FriendId;

import java.util.UUID;

public interface FriendRepository {

    default FriendId nextIdentity() {
        return new FriendId(UUID.randomUUID().toString());
    }

    Friend save(Friend friend);

}
