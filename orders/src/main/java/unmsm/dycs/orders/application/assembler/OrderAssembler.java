package unmsm.dycs.orders.application.assembler;

import org.modelmapper.ModelMapper;

import unmsm.dycs.orders.application.dto.OrderDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderAssembler {

    public Order toEntity(OrderDto OrderCreateDto) {
        ModelMapper _mapper = new ModelMapper();
        _mapper.addMappings(new OrderCreateMapper());
        return _mapper.map(OrderCreateDto, Order.class);
    }
    
    public OrderDto toDto(Order Order) {
        ModelMapper _mapper = new ModelMapper();
        _mapper.addMappings(new OrderDtoListMapper());
        return _mapper.map(Order, OrderDto.class);
    }

}
