package br.com.atlasmundi.atlasmundi.application.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtValidatorFilter extends BasicAuthenticationFilter {

    public static final String HEADER = "Authorization";
    public static final String BEARER = "Bearer ";


    public JwtValidatorFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String attribute = request.getHeader(HEADER);

        if (attribute == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!attribute.startsWith(BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        String token = attribute.replace(BEARER, "");

        var authenticationToken = getAuthToken(token);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthToken(String token) {

        String user = JWT.require(Algorithm.HMAC512(JwtAuthFilter.TOKEN_UUID))
                .build()
                .verify(token).getSubject();

        if (user == null)
            return null;

        return new UsernamePasswordAuthenticationToken(user, null, List.of());
    }
}
