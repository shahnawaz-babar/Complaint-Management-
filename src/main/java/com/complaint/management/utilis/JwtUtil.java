package com.complaint.management.utilis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	//which is greater or equal to 32 byte or 256 bit
	private String SECRET_KEY="MySuperSecretKeyForJWTGeneration1234567890";
	
	private SecretKey getSigningKey()
	{
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}
	
	public String generateToken(String token)
	{
		Map<String,Object> claims=new HashMap<String,Object>();
		return createToken(claims,token);
	}
	
	private String createToken(Map<String,Object> claims, String subject)
	{
		return Jwts.builder()
				.claims(claims)
				.subject(subject)
				.header().empty().add("typ","JWt")
				.and()
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+1000*60*50))
				.signWith(getSigningKey())
				.compact();
	}
	
	
	public String extractUserName(String token)
	{
		Claims claims=extractAllClaims(token);
		return claims.getSubject();
	}
	
	public Claims extractAllClaims(String token)
	{
		return Jwts.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public Boolean validateToken(String token)
	{
		return !isTokenExpired(token);
	}
	
	private Boolean isTokenExpired(String token)
	{
		return tokenExpiration(token).before(new Date());				
	}
	private Date tokenExpiration(String token)
	{
		return extractAllClaims(token).getExpiration();
	}
	
	
	
}
