package com.tutorial.javagraphql.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomFilter  extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;
    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //GET AUTH HEADER
        final String authHeader= request.getHeader("Authorization");
        String token=null;


        //UPDATE VALUES OF TOKEN AND USERNAME
        if(authHeader!= null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);

        }
        //IF TOKEN EXISTS,VALIDATE AND PLACE USER IN SESSION
        if (token!= null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= (UserDetails) customUserDetailsService.loadUserByUsername("abc");

                UsernamePasswordAuthenticationToken passToAuthorizationServer = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                passToAuthorizationServer.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(passToAuthorizationServer);





        }
        filterChain.doFilter(request,response);
    }
}
