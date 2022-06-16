package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepositorySpringData extends JpaRepository<Profile, ProfileId> {

    Optional<Profile> findByLogin(String username);

    Optional<Profile> findByTaxId(String taxId);

    Optional<Profile> findByPhoneNumber(String taxId);

    Optional<Profile> findByEmail(String taxId);

    @Query(value = "SELECT p.* FROM friend f " +
            "INNER JOIN profile p on p.profile_id = ( " +
            "CASE " +
            " WHEN (f.requester_id = :profileId) then f.receiver_id " +
            " ELSE f.requester_id " +
            "END" +
            ") WHERE f.requester_id = :profileId OR f.receiver_id = :profileId", nativeQuery = true)
    List<Profile> findFriendsFromProfileId(UUID profileId);
}
