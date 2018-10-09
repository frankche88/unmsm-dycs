package unmsm.dycs.orders.infrastructure.persistence.jdbi.repository;

import org.jdbi.v3.core.Jdbi;

import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;
import unmsm.dycs.orders.infrastructure.persistence.jdbi.mapper.OrderMapper;

import java.util.List;
import java.util.Optional;

public class OrderJdbiRepository implements OrderRepository {
  private final Jdbi jdbi;

  public OrderJdbiRepository(Jdbi jdbi) {
    this.jdbi = jdbi;
  }

  /* (non-Javadoc)
 * @see unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#create(unmsm.dycs.orders.domain.Order)
 */
@Override
public Order create(Order order) {
    jdbi.inTransaction(handle -> {
      String insert = "INSERT " +
        "INTO orders (order_id, address, order_date, order_status, payment_authcode, order_total) " +
        "VALUES (:order_id, :address, :order_date, :order_status, :payment_authcode, :order_total);";
      return handle.createUpdate(insert)
    	.bind("order_id", order.getOrderId())
        .bind("address", order.getAddress())
        .bind("order_date", order.getOrderDate())
        .bind("order_status", order.getOrderStatus().ordinal())
        .bind("payment_authcode", order.getPaymentAuthCode())
        .bind("order_total", order.getOrderTotal())
        .execute();
    });

    return order;
  }

  /* (non-Javadoc)
 * @see unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#find(java.lang.Long)
 */
@Override
public Optional<Order> find(Long id) {
    return jdbi.withHandle(handle ->
      handle.createQuery("SELECT * from orders where order_id=:id")
        .bind("id", id)
        .map(new OrderMapper())
        .findFirst());
  }

  /* (non-Javadoc)
 * @see unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#delete(java.lang.Long)
 */
@Override
public void delete(Long id) {
    jdbi.inTransaction(handle -> {
      String delete = "DELETE FROM orders where order_id=:id";
      return handle.createUpdate(delete)
        .bind("id", id)
        .execute();
    });
  }

  /* (non-Javadoc)
 * @see unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#findByCustomer(java.lang.Long)
 */
@Override
public List<Order> findByBuyer(Long id) {
    return jdbi.withHandle(handle ->
      handle.createQuery("SELECT * from orders where buyer_id=:id")
        .bind("id", id)
        .map(new OrderMapper())
        .list());
  }
}
