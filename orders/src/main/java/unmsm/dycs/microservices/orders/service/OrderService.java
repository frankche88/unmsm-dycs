package unmsm.dycs.microservices.orders.service;

import java.util.Date;
import java.util.List;

import unmsm.dycs.microservices.orders.dao.OrderDaoImpl;
import unmsm.dycs.microservices.orders.model.Order;

public class OrderService {

  
  private final OrderDaoImpl dao;

  public OrderService(OrderDaoImpl dao) {
    this.dao = dao;
  }

  public Order create(Order order) {

    order.setCreatedAt(new Date());
    return dao.create(order);
  }

  public List<Order> ordersByCustomer(Long id) {
    return dao.findByCustomer(id);
  }

  public void delete(Long id) {
    dao.delete(id);
  }
}
