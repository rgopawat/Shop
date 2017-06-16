package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.ShopInformationDao;
import model.ShopInformation;
import service.ShopInformationService;

@Service
@Transactional
public class ShopInformationServiceImpl implements ShopInformationService {

	@Autowired
	private ShopInformationDao shopInformationDao;

	@Override
	public void create(ShopInformation shopInformation) {
		shopInformationDao.create(shopInformation);
	}

	@Override
	public void update(ShopInformation shopInformation) {
		shopInformationDao.update(shopInformation);
	}

	@Override
	public ShopInformation getShopInformationByName(String shopName) {
		return shopInformationDao.getShopInformationByName(shopName);
	}

	@Override
	public void delete(String shopName) {
		shopInformationDao.delete(shopName);

	}

	@Override
	public List<ShopInformation> getShopInformationByLatiLongi(String latitude, String longitude) {
		return shopInformationDao.getShopInformationByLatiLongi(latitude, longitude);
	}

	@Override
	public List<ShopInformation> getAllShops() {
		return shopInformationDao.getAllShops();
	}
}
