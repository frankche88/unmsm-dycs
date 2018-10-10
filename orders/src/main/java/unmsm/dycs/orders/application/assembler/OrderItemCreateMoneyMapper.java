package unmsm.dycs.orders.application.assembler;

import org.modelmapper.PropertyMap;

import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderItemDto;
import unmsm.dycs.orders.domain.entity.OrderItem;

public class OrderItemCreateMoneyMapper extends PropertyMap<OrderItemDto, OrderItem> {

        @Override
        protected void configure() {

            map().setUnitPrice(Money.soles(source.getUnitPrice()));
            
        }

}
