package com.shop.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import model.ShopInformation;
import service.ShopInformationService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfig.class)
public class ShopInformationControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private ShopInformationService shopInformationService;
	
	@Test
	public void givenShopInformation_whenGetShopInfo_thenReturnJsonArray() throws Exception{
		
		ShopInformation expected = new ShopInformation();
	    expected.setShopName("MyShop1");
	    expected.setShopAddress("Kharadi, Pune");
	    expected.setShopPostCode(411004);
	    expected.setLatitude("22.7109385");
	    expected.setLongitude("73.7109385");
		
		List<ShopInformation> returnInfo = Arrays.asList(expected);
		
		Mockito.when(shopInformationService.getAllShops()).thenReturn(returnInfo);
		
		 mvc.perform(get("/getAllShops")
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].shopName", is(expected.getShopName())))
			      .andExpect(jsonPath("$[0].latitude", is(expected.getLatitude())));
		
	}

}
