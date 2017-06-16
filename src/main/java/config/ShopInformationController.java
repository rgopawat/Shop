package config;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import model.ShopInformation;
import model.ShopResponseBean;
import service.ShopInformationService;

@RestController
@EnableAutoConfiguration
@ComponentScan({ "dao", "model", "service" })
public class ShopInformationController {
	

    @Autowired
    private Environment environment;

	@Autowired
	private ShopInformationService shopInformationService;

	@Autowired
	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(ShopInformationController.class);

	/**
	 * This method will just launch the home page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/", "/home" })
	public ModelAndView homePage(ModelMap model) {

		logger.debug("Entry inside the method of launching the home page");

		model.addAttribute("greeting", "Hi, Welcome to Retail Shop ");
		ModelAndView mav = new ModelAndView("welcome");
		mav.addObject(model);

		logger.debug("Exit the method of launching the home page");

		return mav;
	}

	/**
	 * This method will display will the page for adding new shops
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/display_shop_form" }, method = RequestMethod.GET)
	public ModelAndView displayAddShopForm(ModelMap model) {

		logger.debug("Entry inside the method of launching the page for adding new shops");

		ModelAndView mav = new ModelAndView("add_shop");
		mav.addObject(model);

		logger.debug("Exit the method of launching the page for adding new shops");

		return mav;
	}

	/**
	 * This method will just display will the page for displaying all shops
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/view_all_shops" }, method = RequestMethod.GET)
	public ModelAndView viewAllShopsPage(ModelMap model) {

		logger.debug("Entry inside the method of launching the page for viewing new shops");

		ModelAndView mav = new ModelAndView("view_all_shops");
		mav.addObject(model);

		logger.debug("Exit the method of launching the page for viewing new shops");

		return mav;
	}

	/**
	 * This method will display will the get the data for all the shops
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/getAllShops" }, method = RequestMethod.GET)
	@ResponseBody
	public List<ShopInformation> getAllShopsPage() {

		logger.debug("Entry inside the method of getting the list of all the shops");

		List<ShopInformation> allShops = shopInformationService.getAllShops();

		if (allShops.size() > 0) {
			return allShops;
		} else {
			logger.debug("No shop exits in the database");
			List<ShopInformation> noShopFound = new ArrayList<ShopInformation>();
			return noShopFound;
		}
	}

	/**
	 * This shop will actually add the new shop detail or update in case the
	 * shop is already existing.
	 * 
	 * @param shopInformation
	 * @return
	 */
	@RequestMapping(value = "/add/shops", method = RequestMethod.POST)
	public ShopResponseBean addShop(@RequestBody ShopInformation shopInformation) {

		logger.debug("Entry inside the method of adding a new shop");

		ShopResponseBean responseBean = new ShopResponseBean();
		ShopInformation exisitingShopInfoDB = null;
		try {
			String shopName = shopInformation.getShopName();
			exisitingShopInfoDB = shopInformationService.getShopInformationByName(shopName);
			if (exisitingShopInfoDB != null) {

				logger.info("A Shop already exists for the given details");
				ShopInformation existingShopDetail = new ShopInformation();

				existingShopDetail.setShopName(exisitingShopInfoDB.getShopName());
				existingShopDetail.setShopPostCode(exisitingShopInfoDB.getShopPostCode());
				existingShopDetail.setShopAddress(exisitingShopInfoDB.getShopAddress());
				existingShopDetail.setLatitude(exisitingShopInfoDB.getLatitude());
				existingShopDetail.setLongitude(exisitingShopInfoDB.getLongitude());

				exisitingShopInfoDB.setShopAddress(shopInformation.getShopAddress());
				exisitingShopInfoDB.setShopPostCode(shopInformation.getShopPostCode());

				shopInformationService.update(callGoogleLatiLongi(exisitingShopInfoDB));

				responseBean.setCurrentAddress(exisitingShopInfoDB);
				responseBean.setPreviousAddress(existingShopDetail);

				logger.info("Updated the existing shop in the database with the current provides details");
			} else {

				logger.info("No Shop exists for the given details, hence will create a new shop");

				ShopInformation newShop = new ShopInformation();

				newShop.setShopName(shopInformation.getShopName());
				newShop.setShopPostCode(shopInformation.getShopPostCode());
				newShop.setShopAddress(shopInformation.getShopAddress());

				shopInformationService.create(callGoogleLatiLongi(newShop));
				responseBean.setCurrentAddress(newShop);

				logger.info("Created a new shop with the provided details");
			}
		} catch (Exception ex) {
			logger.error("Error occurred while trying to process the request of adding/updating a new shop" + ex);
		}
		return responseBean;
	}

	/**
	 * This method will interact with Google API, and get the Latitude and
	 * Longitude
	 * 
	 * @param newShop
	 * @return
	 */
	private ShopInformation callGoogleLatiLongi(ShopInformation newShop) {

		logger.debug("Entry inside the method of calling the Google MAP API");

		StringBuilder sb = new StringBuilder();
		String addressPostCode = sb.append(newShop.getShopPostCode()).append("+").append(newShop.getShopAddress())
				.toString();
		
		String googleMapApiUrl = environment.getRequiredProperty("googleMapApiUrl");
		String googleMapApiKey = environment.getRequiredProperty("googleMapApiKey");
		
		
		// String googleApiRevGeocodingUrl =
		// "https://maps.googleapis.com/maps/api/geocode/json?latlng=18.5527212,73.88964450000003&key=AIzaSyBwEL2-91Wa_fVxH6ZeruGscaem93FM_jE";

		String googleApiGeocodingUrl = googleMapApiUrl+"?address=" + addressPostCode + "&key="+googleMapApiKey;
		
		MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<String, String>();
		headersMap.add("content-type", "application/json");

		HttpEntity<?> entity = new HttpEntity<Object>(headersMap);

		ResponseEntity response = this.restTemplate.exchange(googleApiGeocodingUrl, HttpMethod.GET, entity,
				String.class);

		// now it will parse the JSON response and will get only the latitude
		// and longitude
		JSONParser parser = new JSONParser();
		Object obj = null;
		try {
			obj = parser.parse(response.getBody().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		JSONObject jb = (JSONObject) obj;

		JSONArray jsonObject1 = (JSONArray) jb.get("results");
		JSONObject jsonObject2 = (JSONObject) jsonObject1.get(0);
		JSONObject jsonObject3 = (JSONObject) jsonObject2.get("geometry");
		JSONObject location = (JSONObject) jsonObject3.get("location");

		newShop.setLatitude(location.get("lat").toString());
		newShop.setLongitude(location.get("lng").toString());

		logger.debug("Exit the method of calling the Google MAP API");

		return newShop;

	}

	/**
	 * This method will get the shop detail provided the latitude and longitude.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(value = "/getShopByLatiLongi", produces = MediaType.APPLICATION_JSON_VALUE)
	public ShopInformation getShopByLatiLong(@RequestParam("lati") String latitude,
			@RequestParam("longi") String longitude) {

		logger.info("Entry inside the method of getting the shop name provided the latitude and longitudes");

		List<ShopInformation> shopList = shopInformationService.getShopInformationByLatiLongi(latitude, longitude);

		if (shopList.size() > 0) {
			return shopList.get(0);
		} else {
			logger.info("No Shop entry found for the provided latitude and longitude");
			ShopInformation shopInfo = new ShopInformation();
			return shopInfo;
		}
	}
}
