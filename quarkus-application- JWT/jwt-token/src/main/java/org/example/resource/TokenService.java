package org.example.resource;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class TokenService {

    Set<String> roles = new HashSet<>(Arrays.asList("admin", "teacher"));

    public String generateToken(){
        return Jwt.issuer("jwt-token")
                .subject("course")
                .groups(roles)
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
    }
}
