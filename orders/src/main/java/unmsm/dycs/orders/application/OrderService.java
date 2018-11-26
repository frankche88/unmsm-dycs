package unmsm.dycs.orders.application;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import unmsm.dycs.commons.application.Notification;
import unmsm.dycs.commons.infrastructure.message.MessageService;
import unmsm.dycs.commons.infrastructure.message.OrderCompletedEvent;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.entity.OrderItem;
import unmsm.dycs.orders.domain.repository.OrderItemRepository;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository;
    private final OrderItemRepository itemRepository;
    private final MessageService rabbitMessageService;
    private final MessageService firebaseMessageService;
    
    @Inject
    public OrderService(@Named("rabbit")MessageService rabbitService, @Named("firebase")MessageService firebaseService, OrderRepository repository, OrderItemRepository itemRepository) {
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.rabbitMessageService = rabbitService;
        this.firebaseMessageService = firebaseService;
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
    	
    	OrderCompletedEvent event = new OrderCompletedEvent(order.getId());
    	
    	rabbitMessageService.publish(event);
    	
    	firebaseMessageService.publish(event);
        
        return retOrder;
    }

    private Notification validate(Order order) {
    	
    	
    	/**
    	 * 
    	 this.validationMessages = {
      firstName: {
        required: 'Fist Name is required.',
        minlength: 'Fist Name must be at least 2 characters.'
      },
      lastName: {
        required: 'Last Name is required.',
        minlength: 'Last Name must be at least 2 characters.'
      },
      address: {
        required: 'Address is required.',
        minlength: 'Address must be at least 2 characters.'
      },
      creditCardNumber: {
        required: 'Credit Card Number is required.',
        digits: 'Please enter a valid Credit Card Number.',
        rangeLength: 'Credit Card Number must have 16 numbers.'
      },
      creditCardAuthCode: {
        required: 'Credit Card Auth Code is required.',
        digits: 'Please enter a valid Credit Card Auth Code.',
        rangeLength: 'Credit Card Auth Code must have 3 numbers.'
      }
    };
    
    
    
      this.mainForm = this.fb.group({
      firstName: new FormControl('', [Validators.required, Validators.minLength(2)]),
      lastName: new FormControl('', [Validators.required, Validators.minLength(2)]),
      address: new FormControl('', [Validators.required, Validators.minLength(2)]),
      creditCardNumber: new FormControl('', [Validators.required, CustomValidators.digits, CustomValidators.rangeLength([16, 16])]),
      creditCardAuthCode: new FormControl('', [Validators.required, CustomValidators.digits, CustomValidators.rangeLength([3, 3])])
    });
    
    
    	 * 
    	 */
    	
    	
    	
    	
    	
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
