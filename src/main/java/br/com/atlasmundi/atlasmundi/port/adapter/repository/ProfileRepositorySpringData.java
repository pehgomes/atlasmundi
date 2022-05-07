package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepositorySpringData extends JpaRepository<Profile, ProfileId> {

    Optional<Profile> findByLogin(String username);

    Optional<Profile> findByTaxId(String taxId);

    Optional<Profile> findByPhoneNumber(String taxId);

    Optional<Profile> findByEmail(String taxId);
}
