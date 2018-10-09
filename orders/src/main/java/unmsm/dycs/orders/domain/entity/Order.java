package unmsm.dycs.orders.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import unmsm.dycs.commons.domain.enumeration.OrderStatus;
import unmsm.dycs.orders.application.dto.OrderItemDto;

public class Order {
	
	private long orderId;

	private String address;

	private Date orderDate;

	private OrderStatus orderStatus;
	
	private String paymentAuthCode;
	
	private BigDecimal orderTotal;
	
	private Buyer buyer;
	
	private List<OrderItemDto> orderItems = new ArrayList<>();

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentAuthCode() {
		return paymentAuthCode;
	}

	public void setPaymentAuthCode(String paymentAuthCode) {
		this.paymentAuthCode = paymentAuthCode;
	}

	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}
	
	

  
}
