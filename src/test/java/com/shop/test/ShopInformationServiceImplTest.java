package com.shop.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import dao.impl.ShopInformationDaoImpl;
import model.ShopInformation;
import service.ShopInformationService;
import service.impl.ShopInformationServiceImpl;

@RunWith(SpringRunner.class)
public class ShopInformationServiceImplTest {

	@TestConfiguration
	static class ShopInformationServiceImplTestContextConfiguration {

		@Bean
		public ShopInformationService shopInformationService() {
			return new ShopInformationServiceImpl();
		}
	}

	@Autowired
	private ShopInformationService shopInformationService;

	@MockBean
	private ShopInformationDaoImpl shopInformationDaoImpl;

	@Before
	public void setUP() {
	    ShopInformation expected = new ShopInformation();
	    expected.setShopName("MyShop1");
	    expected.setShopAddress("Kharadi, Pune");
	    expected.setShopPostCode(411004);
	    expected.setLatitude("22.7109385");
	    expected.setLongitude("73.7109385");

		Mockito.when(shopInformationDaoImpl.getShopInformationByName(expected.getShopName())).thenReturn(expected);
	}

	@Test
	public void whenValidShopName_thenShopInformationBeFound() {
		String myShopName = "MyShop1";

		ShopInformation found = shopInformationService.getShopInformationByName(myShopName);

		assertThat(found.getShopName()).isEqualTo(myShopName);
	}

}
