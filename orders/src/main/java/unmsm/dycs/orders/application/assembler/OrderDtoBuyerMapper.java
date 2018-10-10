package unmsm.dycs.orders.application.assembler;

import org.modelmapper.Condition;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderDtoBuyerMapper extends PropertyMap<Order, OrderDto> {

	Condition<Order, OrderDto> notNull = new Condition<Order, OrderDto>() {

		public boolean applies(MappingContext<Order, OrderDto> context) {
			return context.getSource().getBuyer() != null;
		}

	};

	@Override
	protected void configure() {

		map().setBuyerId(source.getBuyer().getBuyerId());

		map().setUserName(source.getBuyer().getUserName());

		map().setFirstName(source.getBuyer().getFirstName());

		map().setLastName(source.getBuyer().getLastName());

	}

}
