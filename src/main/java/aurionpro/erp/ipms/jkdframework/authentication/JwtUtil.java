package aurionpro.erp.ipms.jkdframework.authentication;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import aurionpro.erp.ipms.authorization.auropayclient.UserPrincipal;
import aurionpro.erp.ipms.authorization.userprofile.UserProfile;
import aurionpro.erp.ipms.utility.EncryptionUtility;
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

    public Boolean isTokenExpired(String token){
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

	public String generateToken(Authentication authentication, UserDetails userDetails) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		Date now = new Date();
		Date expiryDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2);
		return Jwts.builder()
				.setSubject(new String(EncryptionUtility.encrypt(Long.toString(userPrincipal.getId())),
						StandardCharsets.UTF_8))
				.setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.claim("username",
						new String(EncryptionUtility.encrypt(userPrincipal.getUsername()), StandardCharsets.UTF_8))
				.claim("name", new String(EncryptionUtility.encrypt(userPrincipal.getName()), StandardCharsets.UTF_8))
				.claim("isClient", 1).compact();

	}

	public Integer extractIsClientFlag(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		Integer isClient = 0;
		try {
			isClient = claims.get("isClient", Integer.class);
			if (isClient == null) {
				isClient =0;
			}
		} catch (Exception ex) {
			isClient =0;
		}
		return isClient;
	}
 
	public String getTokenFromRequest(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getHeader("Authorization").substring(7,
				httpServletRequest.getHeader("Authorization").length());

	}
	
	public Long getUserIdFromJWT(HttpServletRequest httpServletRequest) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(getTokenFromRequest(httpServletRequest))
				.getBody();
		String userId=EncryptionUtility.getDecryptedString(claims.getSubject());
		
		return Long.parseLong(userId);
	}
	
	 public String getUserNameFromJWT(String token) {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
			return EncryptionUtility.getDecryptedString(claims.get("username", String.class));
		}
  
}