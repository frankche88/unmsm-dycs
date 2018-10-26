package unmsm.dycs.orders.application.assembler;

import java.math.BigDecimal;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.commons.domain.enumeration.Currency;
import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderItemOutputDto;
import unmsm.dycs.orders.domain.entity.OrderItem;

public class OrderItemOutputDtoMapper extends PropertyMap<OrderItem, OrderItemOutputDto> {

	final Converter<OrderItem, BigDecimal> totalConverter = new Converter<OrderItem, BigDecimal>() {
		public BigDecimal convert(MappingContext<OrderItem, BigDecimal> context) {

			BigDecimal total = context.getSource().calculateTotal().getAmount();

			return total;

		}
	};
	
	final Converter<OrderItem, BigDecimal> unitPriceConverter = new Converter<OrderItem, BigDecimal>() {
        public BigDecimal convert(MappingContext<OrderItem, BigDecimal> context) {

            BigDecimal unitPrice = context.getSource().getUnitPrice().getAmount();

            return unitPrice;

        }
    };
	

	final Converter<OrderItem, String> currencyConverter = new Converter<OrderItem, String>() {
		public String convert(MappingContext<OrderItem, String> context) {

			Money precio = context.getSource().getUnitPrice();

			if (precio == null) {

				return Currency.PEN.name();
			}

			return precio.getCurrency().name();

		}
	};

	@Override
	protected void configure() {

	    using(unitPriceConverter).map(source).setUnitPrice(null);
		using(totalConverter).map(source).setTotal(null);
		//map().setCurrency(source.getUnitPrice().getCurrency().name());
		using(currencyConverter).map(source).setCurrency(null);

	}

}
