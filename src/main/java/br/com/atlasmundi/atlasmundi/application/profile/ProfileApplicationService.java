package br.com.atlasmundi.atlasmundi.application.profile;

import br.com.atlasmundi.atlasmundi.application.command.ProfileCommand;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import br.com.atlasmundi.atlasmundi.domain.ProfileId;
import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ProfileApplicationService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileApplicationService(ProfileRepository profileRepository,
                                     PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ProfileId createAccount(ProfileCommand command) {

        var profile = new Profile(profileRepository.nextId(),
                command.getLogin(),
                passwordEncoder.encode(command.getPassword()),
                command.getTaxId(),
                command.getName(),
                command.getBirthDate(),
                command.getPhoneNumber());

        profileRepository.save(profile);

        return profile.uuid();
    }
}
