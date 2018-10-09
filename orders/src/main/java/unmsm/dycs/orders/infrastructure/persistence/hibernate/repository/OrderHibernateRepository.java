package unmsm.dycs.orders.infrastructure.persistence.hibernate.repository;

import java.util.List;
import java.util.Optional;

import unmsm.dycs.commons.infrastructure.persistence.hibernate.repository.BaseHibernateRepository;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderHibernateRepository extends BaseHibernateRepository<Order> implements OrderRepository {

    @Override
    public Order create(Order order) {

        this.persist(order);
        return order;
    }

    @Override
    public Optional<Order> find(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        Order order = new Order();
        order.setOrderId(id);
        this.getSession().delete(order);

    }

    @Override
    public List<Order> findByBuyer(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
