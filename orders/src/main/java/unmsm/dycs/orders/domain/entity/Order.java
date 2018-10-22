package unmsm.dycs.orders.domain.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private Long id;
	private Long buyerid;
	private String firstName;
	private String lastName;
	private String address;
	private String creditCardNumber;
	private String creditCardAuthCode;
	private Date orderDate;
	
	private Set<OrderItem> orderItems = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getBuyerid() {
		return buyerid;
	}

	public void setBuyerid(Long buyerid) {
		this.buyerid = buyerid;
	}

}
