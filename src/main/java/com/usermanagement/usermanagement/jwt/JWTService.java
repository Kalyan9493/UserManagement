package com.usermanagement.usermanagement.jwt;

import com.usermanagement.usermanagement.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTService {

    private static final String SECRET_KEY = "LZwNcmhfVrGnpSsT86DP79K3EAxMgyRkF5eJ4UdqXBWHa2bzuQD7hYL5Jv9crAHgFujdx8m4qXTQeV2Pnztk3WSNMRbfapwEKB6CgevWrjZA35xz49JsUMGVdnm2uqHQLRDw7SEfYhCcTBKNbkyX8PUQmJhcafdnqX7HkrzTbSvRjM3syA2NV6u5xeKG4tp8gZWCwLPEa8dUyXRqvKw4mFkB3sWtNEL7DPrCgjpAnGSeYzHTf9MQJ6uV2b";
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    public String generateToken(User user){
        return generateToken(new HashMap<>(), user);
    }

    public boolean isTokenValid(String token, User user){
        final String username = extractUsername(token);
        if(username.equals(user.getEmailId()) && !isTokenExpired(token)){
            return true;
        }
        return false;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(Map<String, Object> extraClaims, User user){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmailId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
