package unmsm.dycs.microservices.orders.model;

public enum OrderStatus {
	Preparing(1),
	        Shipped(2),
	        Delivered(3);
	
	OrderStatus(int correl){
		this.correl = correl;
	}
	
	public int getCorrel() {
		return correl;
	}

	private int correl;
	
	

}
