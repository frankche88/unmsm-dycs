package unmsm.dycs.application.security;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import unmsm.dycs.application.security.client.ApplicationUser;

@Provider
public class OAuthDynamicFeature extends AuthDynamicFeature {
    
    Logger logger = LoggerFactory.getLogger(OAuthDynamicFeature.class);

    @Inject
    public OAuthDynamicFeature(OAuthAuthenticator authenticator, UserAuthorizer authorizer, Environment environment) {
        
        super(new OAuthCredentialAuthFilter.Builder<ApplicationUser>().setAuthenticator(authenticator).setAuthorizer(authorizer)
                .setPrefix("Bearer").buildAuthFilter());
        //                  Bearer
        
        
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(ApplicationUser.class));
    }



    
}
