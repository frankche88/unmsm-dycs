package unmsm.dycs.application.security;

import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import unmsm.dycs.application.security.client.ApplicationUser;

@Singleton
public class OAuthAuthenticator implements Authenticator<String, ApplicationUser> {
    
    Logger logger = LoggerFactory.getLogger(OAuthDynamicFeature.class);
    
    private JwtOrderConfiguration  jwtConfig;
    
    @Inject
    public OAuthAuthenticator(JwtOrderConfiguration  jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Optional<ApplicationUser> authenticate(String credentials) throws AuthenticationException {
        // buscar usuario en BD
        try {

            // decode the base64 encoded string
            
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(jwtConfig.getTokenKey().getBytes()).parseClaimsJws(credentials);;
            
            logger.info("jwsClaims: " + jwsClaims);

            String username = jwsClaims.getBody().getSubject();
            
            Long customerId = new Long(jwsClaims.getBody().get("userId").toString());
            
            logger.info("customerId: " + customerId);

            String role = (String) jwsClaims.getBody().get("role");
            
            ApplicationUser user = new ApplicationUser(username, customerId.longValue());

            user.addRole(role);

            return Optional.of(username != null ? user : null);

        } catch (SignatureException e) {

            throw new AuthenticationException("Token not valid", e);
        }
    }
}
