package unmsm.dycs.commons.infrastructure.message;

import unmsm.dycs.commons.infrastructure.message.amqp.AmpqServiceImpl;
import unmsm.dycs.commons.infrastructure.message.amqp.AmpqServiceImpl.OrderCompletedEvent;

public interface MessageService {

	void publish(OrderCompletedEvent event);

}