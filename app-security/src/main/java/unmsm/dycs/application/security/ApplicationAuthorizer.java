package unmsm.dycs.application.security;

import javax.inject.Singleton;

import io.dropwizard.auth.Authorizer;
import unmsm.dycs.application.security.client.ApplicationUser;

@Singleton
public class ApplicationAuthorizer implements Authorizer<ApplicationUser> {

    @Override
    public boolean authorize(ApplicationUser user, String role) {

        if (user == null) {

            return false;
        }

        user.getRoles().contains(role);

        return user.getRoles().contains(role);
    }

}
