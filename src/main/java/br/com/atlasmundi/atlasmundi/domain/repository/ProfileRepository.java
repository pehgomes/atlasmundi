package br.com.atlasmundi.atlasmundi.domain.repository;

import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository {

    Optional<Profile> findByLogin(String username);

    Optional<Profile> findByEmail(String username);

    Optional<Profile> findByPhoneNumber(String username);

    Profile save(Profile profile);

    default ProfileId nextId() {
        return new ProfileId(UUID.randomUUID().toString());
    }

    Optional<Profile> findById(ProfileId profileId);

    Optional<Profile> findByTaxId(String taxId);

    List<Profile> findFriendsFromProfile(ProfileId profileId);
}
