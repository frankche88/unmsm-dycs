package unmsm.dycs.orders.application.assembler;

import org.modelmapper.PropertyMap;

import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderDtoMoneyMapper extends PropertyMap<Order, OrderDto> {

        @Override
        protected void configure() {

            map().setOrderTotal(source.getOrderTotal().getAmount());
            
        }

}
