package unmsm.dycs.orders.application.assembler;

import unmsm.dycs.commons.domain.valueobject.Money;
import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderCreateMapper extends PropertyMap<OrderDto, Order> {

        @Override
        protected void configure() {
            
            //Customer customer = new Customer();
            
            //customer.setId(source.getCustomerId());
            
            map().setCustomer(customer);

            map().setBalance(new Money(source.get, source.getCurrency()));
            
        }

}
