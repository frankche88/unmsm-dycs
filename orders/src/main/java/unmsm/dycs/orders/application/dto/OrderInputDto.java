package unmsm.dycs.orders.application.dto;

import java.util.List;

public class OrderInputDto {

	private String firstName;
	private String lastName;
	private String address;
	private String creditCardNumber;
	private String creditCardAuthCode;
	private List<OrderItemInputDto> orderItems;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCreditCardAuthCode() {
		return creditCardAuthCode;
	}
	public void setCreditCardAuthCode(String creditCardAuthCode) {
		this.creditCardAuthCode = creditCardAuthCode;
	}
	public List<OrderItemInputDto> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemInputDto> orderItems) {
		this.orderItems = orderItems;
	}

}
