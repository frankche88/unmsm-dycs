package unmsm.dycs.orders.application.assembler;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderBuyerMapper extends PropertyMap<OrderDto, Order> {

	Condition<Order, OrderDto> notNull = new Condition<Order, OrderDto>() {

		public boolean applies(MappingContext<Order, OrderDto> context) {
			return context.getSource().getBuyer() != null;
		}

	};

	@Override
	protected void configure() {

		map().getBuyer().setBuyerId(source.getBuyerId());

		map().getBuyer().setUserName(source.getUserName());

		map().getBuyer().setFirstName(source.getFirstName());

		map().getBuyer().setLastName(source.getLastName());

	}

}
