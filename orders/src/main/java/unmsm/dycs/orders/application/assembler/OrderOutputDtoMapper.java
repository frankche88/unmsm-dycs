package unmsm.dycs.orders.application.assembler;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.commons.domain.enumeration.Currency;
import unmsm.dycs.orders.application.dto.OrderOutputDto;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.entity.OrderItem;

public class OrderOutputDtoMapper extends PropertyMap<Order, OrderOutputDto> {

	final Converter<Order, String> fullNameConverter = new Converter<Order, String>() {
		public String convert(MappingContext<Order, String> context) {

			return context.getSource().getFirstName() + " " + context.getSource().getLastName();

		}
	};

	final Converter<Order, String> orderDateConverter = new Converter<Order, String>() {
		public String convert(MappingContext<Order, String> context) {

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			if(context.getSource().getOrderDate() == null) {
				
				return "";
			}
			
			return df.format(context.getSource().getOrderDate());

		}
	};

	final Converter<Order, String> currencyConverter = new Converter<Order, String>() {
		public String convert(MappingContext<Order, String> context) {

			Set<OrderItem> orderItems = context.getSource().getOrderItems();

			if (orderItems.size() > 0) {

				return orderItems.stream().findFirst().get().getUnitPrice().getCurrency().name();
			}

			return Currency.PEN.name();

		}
	};

	final Converter<Order, BigDecimal> totalConverter = new Converter<Order, BigDecimal>() {
		public BigDecimal convert(MappingContext<Order, BigDecimal> context) {

			Set<OrderItem> orderItems = context.getSource().getOrderItems();

			BigDecimal total = BigDecimal.ZERO;

			for (OrderItem orderItem : orderItems) {
				total = total.add(orderItem.calculateTotal().getAmount());
			}

			return total;

		}
	};
	

	@Override
	protected void configure() {

		using(fullNameConverter).map(source).setFullName(null);
		using(totalConverter).map(source).setTotal(null);
		using(currencyConverter).map(source).setCurrency(null);
		using(orderDateConverter).map(source).setOrderDate(null);

	}

}
