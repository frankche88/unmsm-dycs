package unmsm.dycs.microservices.security;

import io.dropwizard.auth.Authorizer;
import unmsm.dycs.microservices.security.client.ApplicationUser;

public class ApplicationAuthorizer implements Authorizer<ApplicationUser> {
  @Override
  public boolean authorize(ApplicationUser principal, String role) {
    //No tenemos aplicada autorización
    return true;
  }
}
