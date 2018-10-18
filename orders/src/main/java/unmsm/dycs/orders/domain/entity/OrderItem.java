package unmsm.dycs.orders.domain.entity;

import java.math.BigDecimal;

import unmsm.dycs.commons.domain.valueobject.Money;

public class OrderItem {

	private Long id;
	private Long orderId;
	private Long productId;
	private String productName;
	private String pictureUrl;
	private BigDecimal unit;
	private Money unitPrice;

	public Money calculateTotal() {
		if (unit != null && unitPrice != null) {
			return new Money(unit.multiply(unitPrice.getAmount()), unitPrice.getCurrency());
		}

		return Money.soles(BigDecimal.ZERO);

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public BigDecimal getUnit() {
		return unit;
	}

	public void setUnit(BigDecimal unit) {
		this.unit = unit;
	}

	public Money getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Money unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}
