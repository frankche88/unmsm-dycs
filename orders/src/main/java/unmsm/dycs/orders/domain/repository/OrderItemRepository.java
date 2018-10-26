package unmsm.dycs.orders.domain.repository;

import unmsm.dycs.orders.domain.entity.OrderItem;

public interface OrderItemRepository {

    OrderItem create(OrderItem order);

}