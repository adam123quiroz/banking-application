package com.packtpub.bankingapplication.security.service;

import com.packtpub.bankingapplication.security.domain.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {
    @Value("${jwt-token-secret}")
    private String plainSecret;
    private String encodedSecret;

    @PostConstruct
    public void init() {
        encodedSecret = Base64.getEncoder().encodeToString(this.plainSecret.getBytes());
    }

    public JwtUser getUser(String token) {
        Claims claims = Jwts.parser().setSigningKey(encodedSecret).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        String role = (String) claims.get("role");
        JwtUser user = new JwtUser();
        user.setUsername(username);
        user.setRole(role);
        return user;
    }

    public String getToken(JwtUser jwtUser) {
        return Jwts.builder().setId(UUID.randomUUID().toString()).setSubject(jwtUser.getUsername()).claim("role", jwtUser.getRole()).setIssuedAt(new Date()).setExpiration(getExpirationTime()).signWith(SignatureAlgorithm.HS512, encodedSecret).compact();
    }

    protected Date getExpirationTime() {
        Date now = new Date();
        int expireHours = 24;
        Long expireInMs = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMs + now.getTime());
    }


}
