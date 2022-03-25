package br.com.atlasmundi.atlasmundi.application.data;

import br.com.atlasmundi.atlasmundi.domain.repository.ProfileRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailServiceAdapter implements UserDetailsService {

    private final ProfileRepository profileRepository;

    public UserDetailServiceAdapter(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = profileRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user with username " + username + " not found"));

        return new UserDetailData(user);
    }
}
