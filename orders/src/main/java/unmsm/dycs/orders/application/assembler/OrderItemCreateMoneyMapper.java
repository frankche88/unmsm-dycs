package unmsm.dycs.orders.application.assembler;

import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;

import unmsm.dycs.commons.domain.enumeration.Currency;
import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderItemDto;
import unmsm.dycs.orders.domain.entity.OrderItem;

public class OrderItemCreateMoneyMapper extends PropertyMap<OrderItemDto, OrderItem> {

    final Converter<OrderItemDto, Money> converter = new Converter<OrderItemDto, Money>() {
        public Money convert(MappingContext<OrderItemDto, Money> context) {

            return new Money(context.getSource().getUnitPrice(), Currency.PEN);

        }
    };

    @Override
    protected void configure() {

        using(converter).map(source).setUnitPrice(null);

    }

}
