package br.com.atlasmundi.atlasmundi.application.config;

import br.com.atlasmundi.atlasmundi.application.data.UserDetailData;
import br.com.atlasmundi.atlasmundi.domain.Profile;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {


    public static final int TOKEN_EXPIRES = 600_000;
    public static final String TOKEN_UUID = "0a68cede-d31c-4c5a-8dd0-621738f10c28";

    private final AuthenticationManager authenticationManager;

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        try {
            Profile profile = new ObjectMapper().readValue(request.getInputStream(), Profile.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    profile.getUsername(),
                    profile.getPassword(),
                    List.of()));

        } catch (IOException e) {
            throw new RuntimeException("error in user authentication", e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        UserDetailData detail = (UserDetailData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(detail.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES ))
                .sign(Algorithm.HMAC512(TOKEN_UUID));

        response.getWriter().write(token);

        response.getWriter().flush();
    }
}
