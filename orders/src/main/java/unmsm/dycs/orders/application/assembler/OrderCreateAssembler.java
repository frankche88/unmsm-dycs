package unmsm.dycs.orders.application.assembler;

import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderCreateAssembler {
    
    private final ModelMapper _mapper;

    public OrderCreateAssembler() {
        _mapper = new ModelMapper();
    }

    public BankAccount toEntity(OrderDto bankAccountCreateDto) {
        _mapper.addMappings(new OrderCreateMapper());
        return _mapper.map(bankAccountCreateDto, Order.class);
    }

}
