package br.com.atlasmundi.atlasmundi.port.adapter.repository;

import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class ProfileRepositoryJpa implements ProfileRepository {

    private final ProfileRepositorySpringData repositorySpringData;

    public ProfileRepositoryJpa(ProfileRepositorySpringData repositorySpringData) {
        this.repositorySpringData = repositorySpringData;
    }

    @Override
    public Optional<Profile> findByLogin(String username) {
        return repositorySpringData.findByLogin(username);
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        return repositorySpringData.findByEmail(email);
    }

    @Override
    public Optional<Profile> findByPhoneNumber(String phoneNumber) {
        return repositorySpringData.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Profile save(Profile profile) {
        return repositorySpringData.save(profile);
    }

    @Override
    public Optional<Profile> findById(ProfileId profileId) {
        return repositorySpringData.findById(profileId);
    }

    @Override
    public Optional<Profile> findByTaxId(String taxId) {
        return repositorySpringData.findByTaxId(taxId);
    }

    @Override
    public List<Profile> findFriendsFromProfile(ProfileId profileId) {
        return repositorySpringData.findFriendsFromProfileId(profileId.uuid());
    }
}
