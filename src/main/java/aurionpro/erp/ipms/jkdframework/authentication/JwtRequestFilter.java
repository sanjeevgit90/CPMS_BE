package aurionpro.erp.ipms.jkdframework.authentication;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import aurionpro.erp.ipms.authorization.auropayclient.AuroPayClientService;
import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.authorization.userprofile.UserProfileService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    
    @Autowired
    private AuroPayClientService auroPayClientService;


    @Autowired
    private UserProfileService uProfileService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
        final String authorizationHeader=request.getHeader("Authorization");

        String username=null;
        String jwt=null;

        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            jwt=authorizationHeader.substring(7);
            username=jwtUtil.extractUserName(jwt);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
        	Integer isClient = jwtUtil.extractIsClientFlag(jwt);
        	if (isClient ==0) {

            MyUserDetails userDetails=userDetailsService.loadUserByUsername(username);
            //userDetails.getAuthorities()
            if(jwtUtil.validateToken(jwt, userDetails)){
                
                //String grantAuthority=jwtUtil.extractAuthority(jwt);
                jwtUtil.setCustomClaims(jwt,userDetails);

                UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(userDetails, null,getAuthorities(username));
                //new UsernamePasswordAuthenticationToken(userDetails, null,null);

                userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationToken);

			}
        	}else {
//        		String username1 = jwtUtil.getUserNameFromJWT(jwt);
//        		   UserDetails userDetails=auroPayClientService.loadUserByUsername(username1);
//                   //userDetails.getAuthorities()
//                   if(!jwtUtil.isTokenExpired(jwt)){
//                	   Authentication aut = new UsernamePasswordAuthenticationToken(userDetails, null);
////                       UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken=
////                       new UsernamePasswordAuthenticationToken(userDetails, null,getAuthorities(username));
//                       //new UsernamePasswordAuthenticationToken(userDetails, null,null);
//
////                       userNamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                     SecurityContextHolder.getContext().setAuthentication(aut);
//
//       			}else {
//       				throw new BadCredentialsException("user not found");
//       			}
//        	}
//         
        }
        }

        filterChain.doFilter(request, response);
        }catch(Exception e) {
        	 throw new RuntimeException("Invalid token");
        }

    }

    public Collection<? extends GrantedAuthority> getAuthorities(String userName) {

        UserProfile uProfile=uProfileService.getUserProfileByName(userName);
        String profileAuthority=uProfile.getAuthorities();
        
        List<GrantedAuthority> authorities;

        authorities=Arrays.stream(profileAuthority.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return authorities;
    }
    
  

}