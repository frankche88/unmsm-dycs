package unmsm.dycs.application.security;

import org.junit.Assert;
import org.junit.Test;

import io.dropwizard.auth.AuthenticationException;
import unmsm.dycs.application.security.OAuthDynamicFeature.OAuthAuthenticator;

public class OAuthAuthenticatorTest {
    
    @Test
    public void test() {
        
        JwtOrderConfiguration jwtConfig = new JwtOrderConfiguration();
        
        jwtConfig.setTokenKey("SUPERPASSWORD123");
        
        
        OAuthAuthenticator oAuthAuthenticator = new OAuthAuthenticator(jwtConfig);
        
        
        String credentials = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjEiLCJ1c2VySWQiOiIxIiwic3ViIjoiYWRtaW4iLCJqdGkiOiIwNDNiNWEzMC02ZTRiLTQ3NDctODgzOS0wNjRmZTViZGE5ODUiLCJlbWFpbCI6ImFkbWluQGhlbnJ5Z3VzdGF2by5jb20iLCJyb2xlIjoiYWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsImV4cCI6MTUzOTMwNjY4MiwiaXNzIjoiaHR0cHM6Ly9zaG9wcGluZ2NhcnQuY29tIiwiYXVkIjoiaHR0cHM6Ly9zaG9wcGluZ2NhcnQuY29tIn0.OrqUMcE05E97aWlvWPgzWuiAX8DgDpTtgU-MLcxy-ek";
        try {
            oAuthAuthenticator.authenticate(credentials );
        } catch (AuthenticationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.fail(e.getLocalizedMessage());
        } catch (Exception e) {
            e.getStackTrace();
            Assert.fail(e.getLocalizedMessage());
            
        }
        
    }

}
