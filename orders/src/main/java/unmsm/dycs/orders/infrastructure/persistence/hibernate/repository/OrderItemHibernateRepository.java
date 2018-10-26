package unmsm.dycs.orders.infrastructure.persistence.hibernate.repository;

import unmsm.dycs.commons.infrastructure.persistence.hibernate.repository.BaseHibernateRepository;
import unmsm.dycs.orders.domain.entity.OrderItem;
import unmsm.dycs.orders.domain.repository.OrderItemRepository;

public class OrderItemHibernateRepository  extends BaseHibernateRepository<OrderItem> implements OrderItemRepository {

    @Override
    public OrderItem create(OrderItem order) {

        this.save(order);
        
        return order;
    }

}
