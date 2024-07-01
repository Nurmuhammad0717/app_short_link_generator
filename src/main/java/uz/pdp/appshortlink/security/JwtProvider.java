package uz.pdp.appshortlink.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.appshortlink.config.AppProperties;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final AppProperties properties;

    public String generateToken(String username){

        Integer expireDays = properties.getJwt().getExpireDays();

        Date expireDate = new Date(System.currentTimeMillis() + expireDays * 24 * 60 * 60 * 1000);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expireDate)
                .signWith(getKey())
                .compact();
    }

    public String getSubject(String token){
        Claims payload = (Claims) Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parse(token)
                .getPayload();
        return payload.getSubject();
    }

    private SecretKey getKey() {
        String secretKey = properties.getJwt().getSecretKey();
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

}
