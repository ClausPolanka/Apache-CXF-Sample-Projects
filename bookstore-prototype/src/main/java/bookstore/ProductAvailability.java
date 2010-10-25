package bookstore;

public class ProductAvailability {

	private final boolean isAvailable;
	private final int estimatedDeliveryTime;

	public ProductAvailability(boolean isAvailable, int estimatedDeliveryTime) {
		this.isAvailable = isAvailable;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public int getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

}
