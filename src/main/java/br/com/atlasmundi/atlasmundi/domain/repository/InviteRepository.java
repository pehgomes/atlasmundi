package br.com.atlasmundi.atlasmundi.domain.repository;

import br.com.atlasmundi.atlasmundi.domain.Invite;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.Profile;

import java.util.Optional;
import java.util.UUID;

public interface InviteRepository {

    default InviteId nextIdentity() {
        return new InviteId(UUID.randomUUID().toString());
    }

    Invite save(Invite invite);

    Optional<Invite> findByIdAndReceiver(InviteId inviteId, Profile receiver);
}
