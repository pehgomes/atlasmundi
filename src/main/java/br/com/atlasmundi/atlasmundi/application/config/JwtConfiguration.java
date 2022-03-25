package br.com.atlasmundi.atlasmundi.application.config;

import br.com.atlasmundi.atlasmundi.application.data.UserDetailServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceAdapter userDetailAdapter;
    private final PasswordEncoder passwordEncoder;

    public JwtConfiguration(UserDetailServiceAdapter userDetailAdapter,
                            PasswordEncoder passwordEncoder) {
        this.userDetailAdapter = userDetailAdapter;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailAdapter).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests() // TODO  remove in prod
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthFilter(authenticationManager()))
                .addFilter(new JwtValidatorFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
