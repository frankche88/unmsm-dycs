package unmsm.dycs.orders.domain.entity;

import unmsm.dycs.commons.domain.valueobject.Money;

public class OrderItem {
	
	private int Id;
	
	private int orderId;
	
	private int productId;

	private String productName;
	
	private String pictureUrl;
	
	private Money unitPrice;

	private int units;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Money getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Money unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


}
