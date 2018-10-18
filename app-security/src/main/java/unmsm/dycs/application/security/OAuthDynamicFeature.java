package unmsm.dycs.application.security;

import java.util.Optional;

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
                
                Long customerId = new Long((Integer)jwsClaims.getBody().get("userId"));
                
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
