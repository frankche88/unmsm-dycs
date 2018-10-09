package unmsm.dycs.orders.application;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderService {

    private final OrderRepository repository;
    
    @Inject
    public OrderService(OrderRepository dao) {
        this.repository = dao;
    }

    public Order create(Order order) {

        order.setOrderDate(new Date());
        return repository.create(order);
    }

    public List<Order> ordersByBuyer(Long id) {
        return repository.findByBuyer(id);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}
