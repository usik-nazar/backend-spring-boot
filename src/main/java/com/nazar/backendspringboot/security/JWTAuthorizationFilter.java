package com.nazar.backendspringboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.nazar.backendspringboot.security.TokenGenerator.SECRET;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager manager) {
        super(manager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(req));
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader("Authorization");

            if (token == null) {
                return null;
            }

            Claims tokenBody = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace("Bearer ", ""))
                    .getBody();

            String user = tokenBody.getSubject();

            if (user == null) return null;

            return new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    null
            );
        } catch (Exception e) {
            return null;
        }
    }
}