package unmsm.dycs.orders.infrastructure.application.guice.module;

import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import unmsm.dycs.AppConfiguration;
import unmsm.dycs.application.security.JwtOrderConfiguration;
import unmsm.dycs.orders.domain.repository.OrderRepository;
import unmsm.dycs.orders.infrastructure.application.bundles.HbnBundle;
import unmsm.dycs.orders.infrastructure.persistence.hibernate.repository.OrderHibernateRepository;

public class HbnModule extends AbstractModule {

	private final HbnBundle hbnBundle;

    public HbnModule(HbnBundle hbnBundle) {
        this.hbnBundle = hbnBundle;
    }

    @Override
    protected void configure() {
        
        bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());
        
        bind(OrderRepository.class).to(OrderHibernateRepository.class);
        
    }
    @Provides
    public JwtOrderConfiguration getRedisConfiguration(AppConfiguration configuration) {
        return configuration.getJwtConfig();
    }

}
