package unmsm.dycs.microservices.orders.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import unmsm.dycs.microservices.orders.model.OrderStatus;

public class OrdersDto {
	


	private int OrderId;

    private String firstName;
    private String lastName;
    public DateTime orderDate;
    private String buyerId;
    private String userName;

    public OrderStatus orderStatus;
   
    private String address;
    private String paymentAuthCode;
   // public Guid RequestId { get;  set; }
    public BigDecimal OrderTotal;
    public List<OrderItemDto> orderItems;
    
    protected OrdersDto() {
        orderItems = new ArrayList<OrderItemDto>();
    }
    
	
	public int getOrderId() {
		return OrderId;
	}

	public void setOrderId(int orderId) {
		OrderId = orderId;
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

	public DateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(DateTime orderDate) {
		this.orderDate = orderDate;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
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
		return OrderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		OrderTotal = orderTotal;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

}
