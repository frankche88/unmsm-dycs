package unmsm.dycs.application.security;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import io.dropwizard.auth.AuthenticationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class OAuthAuthenticatorTest {
    
    @Test
    public void test() throws UnsupportedEncodingException {
        
        JwtOrderConfiguration jwtConfig = new JwtOrderConfiguration();
        
        jwtConfig.setTokenKey("SUPERPASSWORD123");
        
        
        OAuthAuthenticator oAuthAuthenticator = new OAuthAuthenticator(jwtConfig);
        
        
        String credentials = createToken();
        
        System.out.println("OAuthAuthenticatorTest.test()" + credentials);
        
        try {
            oAuthAuthenticator.authenticate(credentials );
        } catch (AuthenticationException e) {
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (Exception e) {
            e.getStackTrace();
            Assert.fail(e.getLocalizedMessage());
            
        }
        
    }
    
    public String createToken() throws UnsupportedEncodingException {
        
        //long now = Instant.now(); //.getEpochSecond();
        
        String s = Jwts.builder()
                .setSubject("admin")
                .setId("043b5a30-6e4b-4747-8839-064fe5bda985")
                .setExpiration(Date.from(Instant.now().plusSeconds(60 * 60 * 24 )))
                .setIssuer("https://shoppingcart.com")
                .setAudience("https://shoppingcart.com")
                .setIssuedAt(Date.from(Instant.now()))
                .claim("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier", "1")
                .claim("userId", "1")
                .claim("email", "admin@henrygustavo.com")
                .claim("role", "admin")
                .claim("http://schemas.microsoft.com/ws/2008/06/identity/claims/role", "Admin")
                .claim("iss", "https://shoppingcart.com")
                .claim("aud", "https://shoppingcart.com")
                .signWith(SignatureAlgorithm.HS256, "SUPERPASSWORD123".getBytes("UTF-8"))
                .compact();
        
        return s;
    }
    
    

}
