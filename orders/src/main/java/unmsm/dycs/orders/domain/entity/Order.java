package unmsm.dycs.orders.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import unmsm.dycs.commons.domain.enumeration.OrderStatus;
import unmsm.dycs.commons.domain.valueobject.Money;

public class Order {
	
	private Long orderId;

	private String address;

	private Date orderDate;

	private OrderStatus orderStatus;
	
	private String paymentAuthCode;
	
	private Money orderTotal;
	
	private Buyer buyer;
	
	private Set<OrderItem> orderItems = new HashSet<>();

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
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

	public Money getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Money orderTotal) {
		this.orderTotal = orderTotal;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	

  
}
