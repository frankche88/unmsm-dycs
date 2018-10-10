package unmsm.dycs.application.security;

import java.security.Key;
import java.util.Base64;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import unmsm.dycs.application.security.client.ApplicationUser;

@Provider
public class OAuthDynamicFeature extends AuthDynamicFeature {
    
    //private static final String JWt_ENCODED_KEY = OAuthDynamicFeature.JWt_ENCODED_KEY;
    public static final String JWt_ENCODED_KEY = "k8zgjphoSZl4aTtNKiOXMQ==";
    
    Logger logger = LoggerFactory.getLogger(OAuthDynamicFeature.class);

    @Inject
    public OAuthDynamicFeature(OAuthAuthenticator authenticator, UserAuthorizer authorizer, Environment environment) {
        super(new OAuthCredentialAuthFilter.Builder<ApplicationUser>().setAuthenticator(authenticator).setAuthorizer(authorizer)
                .setPrefix("Bearer").buildAuthFilter());
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(ApplicationUser.class));
    }

    // classes below may be external (internal for simplicity)

    @Singleton
    public static class OAuthAuthenticator implements Authenticator<String, ApplicationUser> {
        
        Logger logger = LoggerFactory.getLogger(OAuthDynamicFeature.class);

        @Override
        public Optional<ApplicationUser> authenticate(String credentials) throws AuthenticationException {
            // buscar usuario en BD
            try {

                // decode the base64 encoded string
                byte[] decodedKey = Base64.getDecoder().decode(JWt_ENCODED_KEY);
                // rebuild key using SecretKeySpec
                Key secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");

                Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(credentials);
                
                logger.info("jwsClaims: " + jwsClaims);

                String username = jwsClaims.getBody().getSubject();
                
                Long customerId = new Long((Integer)jwsClaims.getBody().get("customerId"));
                
                logger.info("customerId: " + customerId);

                String role = (String) jwsClaims.getBody().get("role");
                
                //{"sub":"client1","customerId":2,"userName":"client1","role":"member"}

                ApplicationUser user = new ApplicationUser(username, customerId.longValue());

                user.addRole(role);

                return Optional.of(username != null ? user : null);

            } catch (SignatureException e) {

                throw new AuthenticationException("Not autenticate");
            }
        }
    }

    @Singleton
    public static class UserAuthorizer implements Authorizer<ApplicationUser> {
        @Override
        public boolean authorize(ApplicationUser user, String role) {
            
            if(user == null) {
                
                return false;
            }
            
            user.getRoles().contains(role);
            
            return user.getRoles().contains(role);
        }
    }
}
