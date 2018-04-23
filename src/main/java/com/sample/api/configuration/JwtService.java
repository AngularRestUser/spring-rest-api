package com.sample.api.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service("jwtService")
@PropertySource("classpath:config-local.properties")
public class JwtService {

    @Value("${jwt.token.secret}")
    private String plainSecret;

    private String encodedSecret;

    @PostConstruct
    protected void init() {
        this.encodedSecret = generateEncodedSecret(this.plainSecret);
    }

    protected String generateEncodedSecret(String plainSecret) {
        if (StringUtils.isEmpty(plainSecret))
        {
            throw new IllegalArgumentException("JWT secret cannot be null or empty.");
        }
        return DatatypeConverter.parseBase64Binary(this.plainSecret).toString();
        //return Base64.getEncoder().encodeToString(this.plainSecret.getBytes());
    }

   
    protected Claims getClaims(String encodedSecret, String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(encodedSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public Claims getAuthenticatedClaims(String authToken) throws JwtException {
    	return getClaims(this.plainSecret, authToken);
    }

    /*protected String getToken(String encodedSecret, String jwtUser) {
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(jwtUser)
                .claim("role", "admin")
                .setIssuedAt(now)
                .setExpiration(getExpirationTime())
                .signWith(SignatureAlgorithm.HS256, encodedSecret)
                .compact();
    }

    public String getToken(String jwtUser) {
        return getToken(this.encodedSecret, jwtUser);
    }*/
}