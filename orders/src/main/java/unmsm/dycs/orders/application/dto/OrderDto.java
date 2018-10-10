package unmsm.dycs.orders.application.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import unmsm.dycs.commons.domain.enumeration.OrderStatus;

public class OrderDto {

	private int orderId;

	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private long buyerId;

	private String address;

	public Date orderDate;

	public OrderStatus orderStatus;
	private String paymentAuthCode;
	public BigDecimal orderTotal;
	public Set<OrderItemDto> orderItems;

	protected OrderDto() {
		orderItems = new HashSet<OrderItemDto>();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Set<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

}
