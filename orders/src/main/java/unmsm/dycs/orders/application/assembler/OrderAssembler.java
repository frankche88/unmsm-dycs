package unmsm.dycs.orders.application.assembler;

import java.util.List;
import java.util.stream.Collectors;

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
		_mapper.addMappings(new OrderDtoBuyerMapper());
		_mapper.addMappings(new OrderDtoMoneyMapper());
		return _mapper.map(Order, OrderDto.class);
	}

	public List<OrderDto> toDto(List<Order> orders) {
		return orders.stream().map(this::toDto).collect(Collectors.toList());
	}

}
