package unmsm.dycs.commons.infrastructure.message.amqp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.health.HealthCheck;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AMQPHealthCheck extends HealthCheck {

	private static final Logger LOG = LoggerFactory.getLogger(AMQPHealthCheck.class);
	private final ConnectionFactory connectionFactory;

	public AMQPHealthCheck(ConnectionFactory connectionFactory) {
		super();
		this.connectionFactory = connectionFactory;
	}

	@Override
	protected Result check() throws Exception {
		Connection conn = null;
		try {
			conn = connectionFactory.newConnection();
			if (conn.isOpen()) {
				return Result.healthy();
			} else {
				return Result.unhealthy("AMQP connection is closed.");
			}
		} catch (IOException e) {
			return Result.unhealthy("Cannot open AMQP connection. " + e.getMessage());
		} finally {
			if (conn != null)
				try {
					LOG.info("Closing AMQP connection.");
					conn.close();
				} catch (IOException e) {
					conn = null;
					LOG.info("Error closing AMQP connection.");
				}
		}
	}

}
