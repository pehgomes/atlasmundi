package br.com.atlasmundi.atlasmundi.application.profile;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class ProfileApplicationService {

    private final ProfileRepository profileRepository;

    public ProfileApplicationService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public ProfileId createAccount(ProfileCommand command) {

        var profile = new Profile(profileRepository.nextId(),
                command.getLogin(),
                command.getPassword(),
                command.getTaxId(),
                command.getName(),
                command.getBirthDate(),
                command.getPhoneNumber());

        profileRepository.save(profile);

        return profile.uuid();
    }
}
