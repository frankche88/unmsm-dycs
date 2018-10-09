package unmsm.dycs.orders.infrastructure.persistence.hibernate.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        return Optional.ofNullable(this.getSession().find(Order.class, id));
        
    }

    @Override
    public void delete(Long id) {
        Order order = new Order();
        order.setOrderId(id);
        this.getSession().delete(order);

    }

    @Override
    public List<Order> findByBuyer(Long id) {
        
        CriteriaBuilder builder = this.getSession().getCriteriaBuilder();
        
        CriteriaQuery<Order> criteria = builder.createQuery(Order.class );
        
        Root<Order> orderRoot = criteria.from( Order.class );
        
        criteria.select( orderRoot );
        
        criteria.where( builder.equal( orderRoot.get("buyer").get("buyerId" ), id ) );
        
        List<Order> orders = this.getSession().createQuery( criteria ).getResultList();

        return orders;
    }

}
