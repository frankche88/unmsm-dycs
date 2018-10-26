package unmsm.dycs.orders.application;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import unmsm.dycs.commons.application.Notification;
import unmsm.dycs.commons.infrastructure.message.MessageService;
import unmsm.dycs.commons.infrastructure.message.amqp.AmpqServiceImpl.OrderCompletedEvent;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.entity.OrderItem;
import unmsm.dycs.orders.domain.repository.OrderItemRepository;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository;
    private final OrderItemRepository itemRepository;
    private final MessageService messageService;
    
    @Inject
    public OrderService(MessageService messageService, OrderRepository repository, OrderItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.messageService = messageService;
    }

    public Order create(Order order) {
    	
    	Notification notification = validate(order);
    	
    	if(notification.hasErrors()) {
    		throw new IllegalArgumentException(notification.errorMessage());
    	}
    	
    	
    	
    	Order retOrder = repository.create(order);
    	
    	for (OrderItem item : retOrder.getOrderItems()) {
    	    
    	    item.setOrderId(retOrder.getId());
    	    
    	    itemRepository.create(item);
            
        }
    	
    	
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

    private Notification validate(Long id) {
        Notification notification = new Notification();
        if(id == null) {
            notification.addError("Order must be present");
        }
        return notification;
    }

    public Order orderById(Long id) {
        
        Notification notification = validate(id);
        
        if(notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
    	
        Optional<Order> opOrder = repository.find(id);
        
        if(!opOrder.isPresent()) {
            
            throw new IllegalArgumentException("Order Not Found");
        }
        
        return repository.find(id).get();
    }

    public List<Order> findAllByBuyerid(long buyerId) {
    	
        return repository.findAllByBuyerid(buyerId);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
