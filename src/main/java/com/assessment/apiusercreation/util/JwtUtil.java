package com.assessment.apiusercreation.util;

import java.util.Date;
import java.security.Key;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	
	private static final String SECRET_KEY = "mySecretTokenKeyForJWT1234567890";
	private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	
	public static String generateToken(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10))
				.signWith(KEY, SignatureAlgorithm.HS256)
				.compact();
	}
	
	public static Claims validateToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(KEY)
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
	
	public static String extractEmail(String token) {
		return validateToken(token).getSubject();
	}
}
