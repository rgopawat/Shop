# Shop

Shop Information API

The Shop Information is an application which provides an API for creating new shops provided
with the Shop Name, Shop Address and Shop Postal Code. Application will call the Google MAPS API to get the Latitude and the
Longitude based on the provided address and will the insert the entire Shop Information in
the DataBase. Also, user can update the Shop Details and also fetch records for the nearest Shop.

PreRequisities

Download and install STS 3.8.4.RELEASE.
Install the Gradle Plugin and configure the environment variables.
Clone the Project Source Code from GITHUB URI: ""
Install POSTMAN in your chrome broswer.

Installing

'gradle clean install' on the local GIT repository.
Ensure all tests are successfull as part of build.

How to Run and Test

Run it as Spring Boot App.
Using POSTMAN test the APIs.

POST URL
http://localhost:8085/add/shops

Sample JSON:
{
"shopName": "ABC",
"shopAddress": "Kalyani nagar, Pune",
"shopPostCode": "411006",
}

GET URL (Sample)
http://localhost:8085/getShopByLatiLongi?lati=18.5463286&longi=73.9033139






