package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Invite;
import br.com.atlasmundi.atlasmundi.domain.InviteId;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InviteRepositorySpringData extends JpaRepository<Invite, InviteId> {

    Optional<Invite> findByInviteIdAndReceiver(InviteId inviteId, Profile receiver);

    Optional<Invite> findByInviteIdAndRequester(InviteId inviteId, Profile requester);
}
