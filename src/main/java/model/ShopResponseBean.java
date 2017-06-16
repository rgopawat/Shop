package model;

public class ShopResponseBean {

	private ShopInformation currentAddress;

	private ShopInformation previousAddress;

	public ShopInformation getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(ShopInformation currentAddress) {
		this.currentAddress = currentAddress;
	}

	public ShopInformation getPreviousAddress() {
		return previousAddress;
	}

	public void setPreviousAddress(ShopInformation previousAddress) {
		this.previousAddress = previousAddress;
	}

	@Override
	public String toString() {
		return "ShopResponseBean [currentAddress=" + currentAddress + ", previousAddress=" + previousAddress + "]";
	}

}
