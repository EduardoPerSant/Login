package com.prueba.eduardo.component;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.prueba.eduardo.app.domain.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtTokenProviderComponent {

    private String secretKey = "ZjJkODFhMjYwZGVhOGExMDBkZDUxNzk4NGU1M2M1NmE3NTIzZDk2OTQyYTgzNGI5Y2RjMjQ5YmQ0ZThjN2FhOQ==";
    private long validityInMilliseconds = 300000; // 5m

    public String createToken(User user) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims parseToken(String token) {
    	try {
	    	Claims claims = Jwts.parser()
	    		    .setSigningKey(secretKey)
	    		    .build()
	    		    .parseClaimsJws(token)
	    		    .getBody();
	    	return claims;
    	}catch (ExpiredJwtException e) {
            throw new RuntimeException("El token ha expirado", e);
        }
    }

    
    public boolean validateToken(String token) {
        try {
        	Claims claims = parseToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (ExpiredJwtException e) {
        	log.info(e);
            return false;
        } catch (JwtException e) {
        	log.info(e);
            return false;
        }
    }
    
    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }
    
}

