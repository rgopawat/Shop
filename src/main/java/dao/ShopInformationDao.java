package dao;

import java.util.List;

import model.ShopInformation;

public interface ShopInformationDao {

	void create(ShopInformation shopInformation);

	void update(ShopInformation shopInformation);

	ShopInformation getShopInformationByName(String shopName);

	List<ShopInformation> getShopInformationByLatiLongi(String latitude, String longitude);

	void delete(String shopName);

	List<ShopInformation> getAllShops();

}
