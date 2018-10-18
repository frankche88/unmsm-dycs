package unmsm.dycs.orders.application.dto;

import java.util.ArrayList;
import java.util.List;

public class OrderOutputDto extends OrderHeaderOutputDto {
	
	public List <OrderItemOutputDto>  orderItems = new ArrayList<OrderItemOutputDto>();

	public List<OrderItemOutputDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemOutputDto> orderItems) {
		this.orderItems = orderItems;
	}

}
