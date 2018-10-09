package unmsm.dycs.orders.domain.entity;

import java.math.BigDecimal;

public class OrderItem {
	
	private int Id;
	
	private int orderId;
	
	private int productId;

	private String productName;
	
	private String pictureUrl;
	
	private BigDecimal unitPrice;

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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
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
