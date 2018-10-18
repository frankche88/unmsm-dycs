package unmsm.dycs.orders.application.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import unmsm.dycs.orders.application.dto.OrderHeaderOutputDto;
import unmsm.dycs.orders.application.dto.OrderInputDto;
import unmsm.dycs.orders.application.dto.OrderOutputDto;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderAssembler {

	public Order toEntity(OrderInputDto orderCreateDto) {
		ModelMapper _mapper = new ModelMapper();

		_mapper.addMappings(new OrderItemMapper());

		return _mapper.map(orderCreateDto, Order.class);
	}
	
	
	public OrderHeaderOutputDto toHeaderDto(Order Order) {
		ModelMapper _mapper = new ModelMapper();
		_mapper.addMappings(new OrderHeaderOutputDtoMapper());
		
		
		return _mapper.map(Order, OrderHeaderOutputDto.class);
	}


	public List<OrderHeaderOutputDto> toHeaderDto(List<Order> orders) {
		return orders.stream().map(this::toHeaderDto).collect(Collectors.toList());
	}
	

	public OrderOutputDto toDto(Order Order) {
		ModelMapper _mapper = new ModelMapper();
		_mapper.addMappings(new OrderHeaderOutputDtoMapper());
		_mapper.addMappings(new OrderItemOutputDtoMapper());
		
		
		return _mapper.map(Order, OrderOutputDto.class);
	}

	public List<OrderOutputDto> toDto(List<Order> orders) {
		return orders.stream().map(this::toDto).collect(Collectors.toList());
	}

}
