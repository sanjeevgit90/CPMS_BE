package aurionpro.erp.ipms.jkdframework.authentication;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import aurionpro.erp.ipms.authorization.auropayclient.AuroPayClient;
import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil{

    private String SECRET_KEY="ShriRam";

    public String extractUserName(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractAuthority(String token)
    {
        final Claims claims=extractAllClaims(token);
        return claims.get("authority",String.class);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public void setCustomClaims(String token,MyUserDetails userDetails)
    {
        final Claims claims=extractAllClaims(token);
        //userDetails.setMyMenus(claims.get("menu",String.class));
        userDetails.setUserProfileId(claims.get("userprofileId",Long.class));
        userDetails.setOrganizationId(claims.get("orgId",Long.class));
        userDetails.setFirstName(claims.get("firstName",String.class));
        userDetails.setLastName(claims.get("lastName",String.class));
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }

    public String generateToken(UserDetails userDetails, UserProfile uProfile, Long orgId){
        Map<String,Object> claims=new HashMap<>();
        //claims.put("authority", uProfile.getAuthorities());
        claims.put("userprofileId", uProfile.getEntityId());
        //claims.put("menu", uProfile.getMyMenus());
        claims.put("firstName", uProfile.getFirstName());
        claims.put("lastName", uProfile.getLastName());
        claims.put("orgId", orgId);
        return CreateToken(claims,userDetails.getUsername());

    }
    
    private String CreateToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    //
//    public String generateTokenClient(AuroPayClient client){
//        Map<String,Object> claims=new HashMap<>();
//        //claims.put("authority", uProfile.getAuthorities());
//        claims.put("clientId", ());
//        //claims.put("menu", uProfile.getMyMenus());
//        claims.put("clientName", uProfile.getFirstName());
//        claims.put("lastName", uProfile.getLastName());
//        claims.put("orgId", orgId);
//        return CreateToken(claims,userDetails.getUsername());
//
//    }
    
  
}