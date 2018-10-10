package unmsm.dycs.orders.application.assembler;

import org.modelmapper.PropertyMap;

import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderCreateMoneyMapper extends PropertyMap<OrderDto, Order> {

        @Override
        protected void configure() {

            map().setOrderTotal(Money.soles(source.getOrderTotal()));
            
        }

}
