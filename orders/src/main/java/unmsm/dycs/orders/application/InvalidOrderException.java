package unmsm.dycs.orders.application;

public class InvalidOrderException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6000960757799888336L;
	private final String customerId;

	public InvalidOrderException(String message, String customerId) {
		super(message);
		this.customerId = customerId;
	}

	public String getOrderId() {
		return customerId;
	}
}
