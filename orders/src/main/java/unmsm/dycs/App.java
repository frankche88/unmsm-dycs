package unmsm.dycs;

import org.jdbi.v3.core.Jdbi;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import unmsm.dycs.orders.api.controllers.OrderResource;
import unmsm.dycs.orders.application.OrderService;
import unmsm.dycs.orders.domain.repository.OrderRepository;
import unmsm.dycs.orders.infrastructure.persistence.jdbi.repository.OrderJdbiRepository;


public class App extends Application<AppConfiguration> {
  public static void main(String[] args) throws Exception {
    new App().run(args);
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
      @Override
      public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    });

    bootstrap.setConfigurationSourceProvider(
      new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor()
      )
    );

  }

  @Override
  public void run(AppConfiguration configuration, Environment environment) throws Exception {
    
    final JdbiFactory factory = new JdbiFactory();
    final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");

    //SecurityUtil.register(configuration.getSecurityServiceBaseUrl(), environment, tracing);

    OrderRepository orderDao = new OrderJdbiRepository(jdbi);

//    CustomerService customerService = new CustomerService(customerServiceClient);
    OrderService orderService = new OrderService(orderDao);

    OrderResource orderResource = new OrderResource(orderService);

    environment.jersey().register(orderResource);
    environment.jersey().register(new InvalidCustomerExceptionMapper());
  }

}
