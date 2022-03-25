package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public class ProfileRepositoryJpa implements ProfileRepository {

    private final ProfileRepositorySpringData repositorySpringData;

    public ProfileRepositoryJpa(ProfileRepositorySpringData repositorySpringData) {
        this.repositorySpringData = repositorySpringData;
    }

    @Override
    public Optional<Profile> findByUsername(String username) {
        return repositorySpringData.findByUsername(username);
    }
}
