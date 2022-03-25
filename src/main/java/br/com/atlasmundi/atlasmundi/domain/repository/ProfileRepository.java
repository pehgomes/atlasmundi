package br.com.atlasmundi.atlasmundi.domain.repository;

import br.com.atlasmundi.atlasmundi.domain.Profile;

import java.util.Optional;

public interface ProfileRepository {

    Optional<Profile> findByUsername(String username);
}
