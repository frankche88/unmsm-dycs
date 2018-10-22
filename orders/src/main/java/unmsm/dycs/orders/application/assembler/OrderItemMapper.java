package unmsm.dycs.orders.application.assembler;

import java.math.BigDecimal;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.commons.domain.enumeration.Currency;
import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderItemInputDto;
import unmsm.dycs.orders.domain.entity.OrderItem;

public class OrderItemMapper extends PropertyMap<OrderItemInputDto, OrderItem> {

	final Converter<OrderItemInputDto, Money> moneyConverter = new Converter<OrderItemInputDto, Money>() {
		
		public Money convert(MappingContext<OrderItemInputDto, Money> context) {

			if (context.getSource() != null || context.getSource().getUnitPrice() != null
					|| context.getSource().getCurrency() != null) {

				Currency currency = Currency.PEN;
				try {
					currency = Currency.valueOf(context.getSource().getCurrency());
				} catch(IllegalArgumentException e) {
					
				}
				
				return new Money(context.getSource().getUnitPrice(), currency == null ? Currency.PEN : currency);

			}

			return Money.soles(BigDecimal.ZERO);

		}
	};

	@Override
	protected void configure() {

		using(moneyConverter).map(source).setUnitPrice(null);

	}

}
