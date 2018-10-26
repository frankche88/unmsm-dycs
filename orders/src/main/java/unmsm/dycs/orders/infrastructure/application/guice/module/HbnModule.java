package unmsm.dycs.orders.infrastructure.application.guice.module;

import java.io.IOException;

import org.hibernate.SessionFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import unmsm.dycs.AppConfiguration;
import unmsm.dycs.application.security.JwtOrderConfiguration;
import unmsm.dycs.commons.infrastructure.message.MessageService;
import unmsm.dycs.commons.infrastructure.message.amqp.AMQPConfiguration;
import unmsm.dycs.commons.infrastructure.message.amqp.AmpqServiceImpl;
import unmsm.dycs.orders.domain.repository.OrderItemRepository;
import unmsm.dycs.orders.domain.repository.OrderRepository;
import unmsm.dycs.orders.infrastructure.application.bundles.HbnBundle;
import unmsm.dycs.orders.infrastructure.persistence.hibernate.repository.OrderHibernateRepository;
import unmsm.dycs.orders.infrastructure.persistence.hibernate.repository.OrderItemHibernateRepository;

public class HbnModule extends AbstractModule {

	private final HbnBundle hbnBundle;

	public HbnModule(HbnBundle hbnBundle) {
		this.hbnBundle = hbnBundle;
	}

	@Override
	protected void configure() {

		bind(SessionFactory.class).toInstance(hbnBundle.getSessionFactory());

		bind(OrderRepository.class).to(OrderHibernateRepository.class);
		
		bind(OrderItemRepository.class).to(OrderItemHibernateRepository.class);
		
		bind(MessageService.class).to(AmpqServiceImpl.class);

	}

	@Provides
	public JwtOrderConfiguration getJwtConfiguration(AppConfiguration configuration) {
		return configuration.getJwtConfig();
	}

	@Provides
	public AMQPConfiguration getAmqpConnectionFactory(AppConfiguration configuration) throws IOException {
		
		return configuration.getAMQPConfiguration();
		
	}
}
