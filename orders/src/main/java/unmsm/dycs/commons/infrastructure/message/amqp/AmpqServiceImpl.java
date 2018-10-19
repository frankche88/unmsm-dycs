package unmsm.dycs.commons.infrastructure.message.amqp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AmpqServiceImpl {

	private final AMQPConfiguration amqpConfig;
	

	@Inject
	public AmpqServiceImpl(AMQPConfiguration amqpConfiguration) {

		this.amqpConfig = amqpConfiguration;

	}

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
			throw new IllegalArgumentException("URL de conexion no valida");
		}

		try {
			channel.queueDeclare(amqpConfig.getQueue(), false, false, false, null);
		} catch (IOException e) {
			throw new IllegalArgumentException("No se pudo acceder a la cola:" + amqpConfig.getQueue());
		}

		try {
			channel.basicPublish("", amqpConfig.getQueue(), null, event.toString().getBytes());
		} catch (IOException e) {
			throw new IllegalArgumentException("No se pudo publicar el mensaje:" + event.toString());
		}

	}

	public static class OrderCompletedEvent {
		public OrderCompletedEvent(String buyerId) {
			this.buyerId = buyerId;
		}

		public String buyerId;

		public String getBuyerId() {
			return buyerId;
		}

		public void setBuyerId(String buyerId) {
			this.buyerId = buyerId;
		}

		public String toString() {
			return "{\"buyerId\": \"" + buyerId + "\"}";
		}
	}

	private ConnectionFactory createConnectionFactory(AMQPConfiguration amqpConfig)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
		ConnectionFactory factory = new ConnectionFactory();

		factory.setUri(amqpConfig.getMqUrl());

		return factory;
	}

}
