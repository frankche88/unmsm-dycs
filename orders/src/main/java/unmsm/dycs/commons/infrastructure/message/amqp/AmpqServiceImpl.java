package unmsm.dycs.commons.infrastructure.message.amqp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import unmsm.dycs.commons.infrastructure.message.MessageService;

public class AmpqServiceImpl implements MessageService {

	private final AMQPConfiguration amqpConfig;

	@Inject
	public AmpqServiceImpl(AMQPConfiguration amqpConfiguration) {

		this.amqpConfig = amqpConfiguration;

	}

	/* (non-Javadoc)
	 * @see unmsm.dycs.commons.infrastructure.message.amqp.MessageService#publish(unmsm.dycs.commons.infrastructure.message.amqp.AmpqServiceImpl.OrderCompletedEvent)
	 */
	@Override
	public void publish(OrderCompletedEvent event) {

		ConnectionFactory factory = null;
		try {
			factory = createConnectionFactory(amqpConfig);
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException | URISyntaxException e) {
			throw new IllegalArgumentException("URL de conexion no valida");
		}

		Connection connection;
		final Channel channel;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
		} catch (IOException e) {
			throw new IllegalArgumentException("URL de conexion no valida", e);
		} catch (TimeoutException e) {
			throw new IllegalArgumentException("Servicio no responde", e);
		}

		try {
			channel.queueDeclare(amqpConfig.getQueue(), true, false, false, null);
		} catch (IOException e) {
			throw new IllegalArgumentException("No se pudo acceder a la cola:" + amqpConfig.getQueue(), e);
		}

		try {
			channel.basicPublish("", amqpConfig.getQueue(), null, event.toString().getBytes());
		} catch (IOException e) {
			throw new IllegalArgumentException("No se pudo publicar el mensaje:" + event.toString(), e);
		}

	}

	public static class OrderCompletedEvent {
		public OrderCompletedEvent(Long buyerId) {
			this.buyerId = buyerId;
		}

		public Long buyerId;

		public Long getBuyerId() {
			return buyerId;
		}

		public void setBuyerId(Long buyerId) {
			this.buyerId = buyerId;
		}

		public String toString() {
			return "" + buyerId;
		}
	}

	private ConnectionFactory createConnectionFactory(AMQPConfiguration amqpConfig)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(amqpConfig.getMqUrl());

		return factory;
	}

}
