package com.abdullahturhan.reservation.filter;

import com.abdullahturhan.reservation.service.CustomerService;
import com.abdullahturhan.reservation.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomerService customerService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
         if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
             filterChain.doFilter(request, response);
             return;
         }

         jwt = authHeader.substring(7);
         username = jwtService.extractUsername(jwt);
         if(!username.isEmpty()  && SecurityContextHolder.getContext().getAuthentication() == null){
             UserDetails userDetails =  customerService.userDetailsService().loadUserByUsername(username);
             if(jwtService.isTokenValid(jwt,userDetails)){
                 SecurityContext context = SecurityContextHolder.createEmptyContext();
                 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                         userDetails,null,userDetails.getAuthorities());
                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 context.setAuthentication(authenticationToken);
                 SecurityContextHolder.setContext(context);
             }
         }

         filterChain.doFilter(request,response);



    }
}
