package unmsm.dycs.orders.application;

public class InvalidCustomerException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6000960757799888336L;
	private final String customerId;

	public InvalidCustomerException(String message, String customerId) {
		super(message);
		this.customerId = customerId;
	}

	public String getCustomerId() {
		return customerId;
	}
}
