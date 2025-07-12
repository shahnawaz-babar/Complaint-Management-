package com.complaint.management.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.complaint.management.utilis.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorizationHeader=request.getHeader("Authorization");				
		String jwt=null;
		String userName=null;
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer"))
		{
			jwt=authorizationHeader.substring(7);
			userName=jwtUtil.extractUserName(jwt);			
		}
		if(userName!=null)
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			if(jwtUtil.validateToken(jwt))
			{
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userName,null,userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		filterChain.doFilter(request, response);
		
		
	}

	
	
	
}
