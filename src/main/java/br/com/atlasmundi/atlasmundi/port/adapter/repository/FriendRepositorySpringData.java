package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FriendRepositorySpringData extends JpaRepository<Friend, FriendId> {

    @Query(value = "SELECT f.* FROM friend f WHERE f.requester_id = :profileId OR f.receiver_id = :profileId ", nativeQuery = true)
    List<Friend> findByRequesterOrReceiver(UUID profileId);
}
