package unmsm.dycs.orders.application;

import java.util.Date;
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

        order.setOrderDate(new Date());
        return repository.create(order);
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
