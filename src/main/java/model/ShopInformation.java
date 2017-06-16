package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shop_information")
public class ShopInformation {

	public ShopInformation() {
	}

	public ShopInformation(String shopName) {
		this.shopName = shopName;
	}

	public ShopInformation(String shopName, String shopAddress, int shopPostCode, String latitude, String longitude) {
		super();
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopPostCode = shopPostCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Id
	@Column(name = "shop_name")
	private String shopName;

	@Column(name = "shop_address")
	private String shopAddress;

	@Column(name = "shop_postcode")
	private int shopPostCode;

	@Column(name = "latitude")
	private String latitude;

	@Column(name = "longitude")
	private String longitude;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public int getShopPostCode() {
		return shopPostCode;
	}

	public void setShopPostCode(int shopPostCode) {
		this.shopPostCode = shopPostCode;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
}
