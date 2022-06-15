package com.nazar.backendspringboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.nazar.backendspringboot.security.TokenGenerator.SECRET;

public class JWTAuthorizationFilter extends GenericFilterBean {

    private UsernamePasswordAuthenticationToken getAuthentication(ServletRequest request) {
        try {
            String token = ((HttpServletRequest) request).getHeader("Authorization");

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

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(req));
        chain.doFilter(req, res);
    }
}