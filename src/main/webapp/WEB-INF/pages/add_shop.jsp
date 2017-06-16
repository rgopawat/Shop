<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<style>

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

.w3-ripple{
color: deepskyblue !important;
}

form{
border: 2px solid rgba(30, 167, 233, 0.22) !important;
margin-top:10px !important;
}

.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
color: rgba(222, 30, 255, 0.78) !important;
}

.w3-border{
border : 1px solid rgba(222, 30, 255, 0.78) !important;
}

.w3-green, .w3-hover-green:hover{
background-color: rgba(156, 39, 176, 0.58) !important;
color: #9c27b0 !important;
}

</style>

<!-- GOOGLE API KEY ::: AIzaSyBwEL2-91Wa_fVxH6ZeruGscaem93FM_jE -->

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Shop Retailer - Add Shops</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>


</head>
<body ng-app="myApp" ng-controller="userCtrl">

<div class="w3-container">

<h2>Shop Retailer - Add Shops</h2>

<a href="<c:url value="/view_all_shops" />" class="w3-btn w3-ripple homeAlign">&#10004;View All Shops</a>

<h3  ng-show="displayAddMessage" class="displaySuccessMessageClass">{{addMessageResult}}</h4>		

<form>
  <h4><b>Add New Shop:</b></h4>
    <label>Shop Name:</label>
    <input class="w3-input w3-border" type="text" ng-model="shopName" placeholder="Enter shop name to be added">
  <br>
    <label>Shop Address:</label>
    <input class="w3-input w3-border" type="text" ng-model="shopAddress"  placeholder="Enter shop address to be added">
  <br>
  <br>
    <label>Shop Postal Code:</label>
    <input class="w3-input w3-border" type="number" ng-model="shopPostalCode"  placeholder="Enter shop postal code to be added">
  <br>
  
  <button class="w3-btn w3-green w3-ripple" ng-click="createNewShop()" ng-disabled="incomplete">&#10004; Save Changes</button> 

</form>

</div>

<script type="text/javascript">
	
	var welcomeHome = angular.module("myApp", []);

	welcomeHome.controller('userCtrl', ['$scope', '$http', function($scope,$http) {
		
	
	$scope.shopName='';
	$scope.shopAddress='';
	$scope.shopPostalCode='';
	$scope.addMessageResult;
	$scope.displayAddMessage = false;
	

	$scope.$watch('shopName',function() {$scope.test();});
	$scope.$watch('shopAddress',function() {$scope.test();});
	$scope.$watch('shopPostalCode',function() {$scope.test();});
	

	$scope.test = function() {
	  $scope.incomplete = false;
	  if (($scope.shopName.length==0) || ($scope.shopAddress.length==0) || ($scope.shopPostalCode==0)) {
	     $scope.incomplete = true;
	  }
	};
	
	$scope.createNewShop = function() {
		var url = "/add/shops";
		
		var dataObj= {
			shopName : $scope.shopName,
			shopAddress : $scope.shopAddress,
			shopPostCode : $scope.shopPostalCode,
		};		
		
		var res = $http.post(url,dataObj);
				  
		res.success(function(data,status,headers,config){
			$scope.displayAddMessage = true;
			$scope.addMessageResult = "Shop "+$scope.shopName+" added successfully";
		});
		res.error(function(data,status,headers,config){
			$scope.displayAddMessage = true;
			$scope.addMessageResult = "Some issue in adding the shop "+$scope.shopName+" ";
		});            		
		
	}
	}]);


</script>

</body>
</html>
