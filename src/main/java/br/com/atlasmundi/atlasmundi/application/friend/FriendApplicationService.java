package br.com.atlasmundi.atlasmundi.application.friend;

import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.stereotype.Component;

@Component
public class FriendApplicationService {

    private final ProfileRepository profileRepository;

    public FriendApplicationService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

}
