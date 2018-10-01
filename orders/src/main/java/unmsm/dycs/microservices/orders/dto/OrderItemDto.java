package unmsm.dycs.microservices.orders.dto;

import java.math.BigDecimal;

public class OrderItemDto {

	public int Id;

	public String ProductName;
	public String PictureUrl;
	public BigDecimal UnitPrice;

	public int units;
	public int productId;

	protected OrderItemDto() {
	}

	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getPictureUrl() {
		return PictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		PictureUrl = pictureUrl;
	}

	public BigDecimal getUnitPrice() {
		return UnitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		UnitPrice = unitPrice;
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
