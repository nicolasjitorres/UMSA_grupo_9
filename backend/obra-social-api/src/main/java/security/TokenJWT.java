package security;

import java.util.Set;

import io.smallrye.jwt.build.Jwt;

public class TokenJWT {
    public static String generateToken(String email, Set<String> roles) {
        if (roles.contains("USER") && roles.contains("ADMIN")) {
            throw new IllegalArgumentException("A user cannot have both USER and ADMIN roles");
        }
        return Jwt.issuer("auth-service")
                .upn(email)
                .groups(roles)
                .sign();
    }
}
