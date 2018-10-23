package unmsm.dycs.orders.domain.repository;

import java.util.List;
import java.util.Optional;

import unmsm.dycs.orders.domain.entity.Order;

public interface OrderRepository {

	Order create(Order order);

	Optional<Order> find(Long id);

	void delete(Long id);

	List<Order> findAll();

	List<Order> findAllByBuyerid(long buyerId);

}