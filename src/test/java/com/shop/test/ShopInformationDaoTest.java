package com.shop.test;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dao.ShopInformationDao;
import model.ShopInformation;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes=TestConfig.class)
public class ShopInformationDaoTest {

	//TODO:private EmbeddedDatabase db;
	
	@Autowired
	TestEntityManager entityManager;
	
	
	@Autowired
	ShopInformationDao shopInformationDao;
	
	@Before
	public void setUp() throws Exception {
		/* TODO:check this
		db = new EmbeddedDatabaseBuilder()
	    		.setType(EmbeddedDatabaseType.H2)
	    		.addScript("create-db.sql")
	    		.addScript("insert-data.sql")
	    		.build();
		*/
	}

	@After
	public void tearDown() throws Exception {
		//db.shutdown();
	}

	/* TODO:check this
	@Test
	public void testGetShopInformationByName() {
		
    	ShopInformation shopInfo = shopInformationDao.getShopInformationByName("ravi");


    	Assert.assertNotNull(shopInfo);
    	Assert.assertEquals("Kharadi, Pune", shopInfo.getShopAddress());
    	Assert.assertEquals("411004", shopInfo.getShopPostCode());
    	Assert.assertEquals("22.7109385", shopInfo.getLatitude());
    	Assert.assertEquals("73.7109385", shopInfo.getLongitude());
	}*/


@Test
public void whenGetShopInformationByName_thenReturnShopInformation() {
    // given
    ShopInformation expected = new ShopInformation();
    expected.setShopName("MyShop1");
    expected.setShopAddress("Kharadi, Pune");
    expected.setShopPostCode(411004);
    expected.setLatitude("22.7109385");
    expected.setLongitude("73.7109385");
    entityManager.persist(expected);
    entityManager.flush();
 
    // when
    ShopInformation actual = shopInformationDao.getShopInformationByName("MyShop1");
 
    // then
    assertThat(actual.getShopName())
      .isEqualTo(expected.getShopName());    
    assertThat(actual.getLatitude())
    .isEqualTo(expected.getLatitude());

}
	
}
