package unmsm.dycs.orders.infrastructure.persistence.jdbi.repository;

import org.jdbi.v3.core.Jdbi;

import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

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
        "INTO orders (created_at, amount, customer_id, delivery_address_id) " +
        "VALUES (:created_at, :amount, :customer_id, :delivery_address_id);";
      return handle.createUpdate(insert)
        .bind("created_at", order.getCreatedAt())
        .bind("amount", order.getAmount())
        .bind("customer_id", order.getCustomerId())
        .bind("delivery_address_id", order.getDeliveryAddressId())
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
      handle.createQuery("SELECT * from orders where id=:id")
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
      String delete = "DELETE FROM orders where id=:id";
      return handle.createUpdate(delete)
        .bind("id", id)
        .execute();
    });
  }

  /* (non-Javadoc)
 * @see unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#findByCustomer(java.lang.Long)
 */
@Override
public List<Order> findByCustomer(Long id) {
    return jdbi.withHandle(handle ->
      handle.createQuery("SELECT * from orders where customer_id=:id")
        .bind("id", id)
        .map(new OrderMapper())
        .list());
  }
}
