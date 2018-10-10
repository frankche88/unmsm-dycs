package unmsm.dycs.orders.application.assembler;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.commons.domain.enumeration.Currency;
import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderCreateMoneyMapper extends PropertyMap<OrderDto, Order> {

    final Converter<OrderDto, Money> converter = new Converter<OrderDto, Money>() {
        public Money convert(MappingContext<OrderDto, Money> context) {

            return new Money(context.getSource().getOrderTotal(), Currency.PEN);

        }
    };

    @Override
    protected void configure() {

        using(converter).map(source).setOrderTotal(null);

    }

}
