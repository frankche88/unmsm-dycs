package unmsm.dycs.orders.infrastructure.application.bundles;

import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import unmsm.dycs.AppConfiguration;

public class SwitchableSwaggerBundle extends SwaggerBundle<AppConfiguration> {

    @Override
    protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(AppConfiguration configuration) {
        return configuration.getSwaggerBundleConfiguration();
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        super.run(configuration, environment);
    }
}
