package unmsm.dycs.orders.application;

import java.util.Date;
import java.util.List;

import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderService {

  
  private final OrderRepository dao;

  public OrderService(OrderRepository dao) {
    this.dao = dao;
  }

  public Order create(Order order) {

    order.setOrderDate(new Date());
    return dao.create(order);
  }

  public List<Order> ordersByCustomer(Long id) {
    return dao.findByCustomer(id);
  }

  public void delete(Long id) {
    dao.delete(id);
  }
}
