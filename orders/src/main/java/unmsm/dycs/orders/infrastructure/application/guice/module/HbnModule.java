package unmsm.dycs.orders.infrastructure.application.guice.module;

import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;

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

}
