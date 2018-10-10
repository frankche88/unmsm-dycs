package unmsm.dycs.orders.application;

import java.util.List;

import javax.inject.Inject;

import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository;
    
    @Inject
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order create(Order order) {
        
        return repository.create(order);
    }

    public List<Order> ordersByBuyer(Long id) {
    	
    	
    	
        return repository.findByBuyer(id);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
