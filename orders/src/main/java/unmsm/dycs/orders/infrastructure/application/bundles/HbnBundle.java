package unmsm.dycs.orders.infrastructure.application.bundles;

import java.io.InputStream;

import com.google.common.collect.ImmutableList;

import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import unmsm.dycs.AppConfiguration;
import unmsm.dycs.orders.infrastructure.persistence.hibernate.repository.OrderHibernateRepository;

public class HbnBundle extends HibernateBundle<AppConfiguration> {

	
	public HbnBundle() {
		super(ImmutableList.of(), new SessionFactoryFactory());
	}

	@Override
	public PooledDataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
		return configuration.getDataSourceFactory();

	}
	
	protected void configure(org.hibernate.cfg.Configuration configuration) {
		
		InputStream inputMovie = OrderHibernateRepository.class.getClassLoader().getResourceAsStream("hibernate/Buyer.hbm.xml");
		
		InputStream inputDirector = OrderHibernateRepository.class.getClassLoader().getResourceAsStream("hibernate/Order.hbm.xml");
		
		InputStream inputCustomer = OrderHibernateRepository.class.getClassLoader().getResourceAsStream("hibernate/OrderItem.hbm.xml");
		
		configuration.addInputStream(inputMovie);
		configuration.addInputStream(inputDirector);
		configuration.addInputStream(inputCustomer);

		
    }

}
