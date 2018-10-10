package unmsm.dycs.orders.application.assembler;

import org.modelmapper.PropertyMap;

import unmsm.dycs.orders.application.dto.OrderItemDto;
import unmsm.dycs.orders.domain.entity.OrderItem;

public class OrderDtoMoneyMapper extends PropertyMap<OrderItem, OrderItemDto> {

        @Override
        protected void configure() {

            map().setUnitPrice(source.getUnitPrice().getAmount());
            
        }

}
