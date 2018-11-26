package unmsm.dycs.commons.infrastructure.message;

public interface MessageService {

	void publish(OrderCompletedEvent event);

}