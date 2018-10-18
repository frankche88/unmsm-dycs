package unmsm.dycs.orders.infrastructure.persistence.jdbi.repository;

import java.util.List;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;

import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;
import unmsm.dycs.orders.infrastructure.persistence.jdbi.mapper.OrderMapper;

public class OrderJdbiRepository implements OrderRepository {
	private final Jdbi jdbi;

	public OrderJdbiRepository(Jdbi jdbi) {
		this.jdbi = jdbi;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#create(
	 * unmsm.dycs.orders.domain.Order)
	 */
	@Override
	public Order create(Order order) {

		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#find(java.
	 * lang.Long)
	 */
	@Override
	public Optional<Order> find(Long id) {
		return jdbi.withHandle(handle -> handle.createQuery("SELECT * from orders where order_id=:id").bind("id", id)
				.map(new OrderMapper()).findFirst());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * unmsm.dycs.orders.infrastructure.persistence.jdbi.OrderRepository#delete(java
	 * .lang.Long)
	 */
	@Override
	public void delete(Long id) {
		jdbi.inTransaction(handle -> {
			String delete = "DELETE FROM orders where order_id=:id";
			return handle.createUpdate(delete).bind("id", id).execute();
		});
	}

	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
