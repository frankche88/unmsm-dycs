package unmsm.dycs.orders.infrastructure.persistence.hibernate.repository;

import java.util.List;
import java.util.Optional;

import unmsm.dycs.commons.infrastructure.persistence.hibernate.repository.BaseHibernateRepository;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderHibernateRepository extends BaseHibernateRepository<Order> implements OrderRepository {

    @Override
    public Order create(Order order) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Order> find(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<Order> findByCustomer(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
