package unmsm.dycs.orders.application;

import java.util.List;

import javax.inject.Inject;

import unmsm.dycs.commons.application.Notification;
import unmsm.dycs.commons.infrastructure.message.MessageService;
import unmsm.dycs.commons.infrastructure.message.amqp.AmpqServiceImpl.OrderCompletedEvent;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository;
    private final MessageService messageService;
    
    @Inject
    public OrderService(MessageService messageService, OrderRepository repository) {
        this.repository = repository;
        this.messageService = messageService;
    }

    public Order create(Order order) {
    	
    	Notification notification = validate(order);
    	
    	if(notification.hasErrors()) {
    		throw new IllegalArgumentException(notification.errorMessage());
    	}
    	
    	Order retOrder = repository.create(order);
    	
    	
    	messageService.publish(new OrderCompletedEvent(order.getId()) );
        
        return retOrder;
    }

    private Notification validate(Order order) {
    	Notification notification = new Notification();
    	if(order.getBuyerid() == null) {
    		notification.addError("Buyer not be null");
    	}
    	
    	if(order.getOrderItems().size() == 0) {
    		notification.addError("Order items must be exist");
    		
    	}
  
		return notification;
	}

    public Order orderById(Long id) {
    	
        return repository.find(id).get();
    }

    public List<Order> findAll() {
    	
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
