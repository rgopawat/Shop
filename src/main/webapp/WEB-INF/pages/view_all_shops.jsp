<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>

<style>

table, th, td {
border: 2px solid rgba(30, 167, 233, 0.22) !important;
}

h1, h2, h3, h4, h5, h6{
color: mediumpurple !important;
}

.w3-table td, .w3-table th, .w3-table-all td, .w3-table-all th{
color:#9c27b0;
}

.w3-ripple{
color: deepskyblue !important	;
}


.displaySuccessMessageClass{
text-align: center !important;
font-weight: bold !important;
color: rgba(222, 30, 255, 0.78) !important;
}

.w3-border{
border : 1px solid rgba(222, 30, 255, 0.78) !important;
}

th{
background-color: rgba(30, 167, 233, 0.22) !important;
}


.w3-green, .w3-hover-green:hover{
background-color: rgba(156, 39, 176, 0.58) !important;
color: #9c27b0 !important;
}

</style>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Shop Retailer - View All Shops</title>

<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>


</head>
<body ng-app="myApp" ng-controller="userCtrl">

<div class="w3-container">

<h2>Shop Retailer - View All Shops</h2>
<br></br>


<a href="<c:url value="/display_shop_form" />" class="w3-btn w3-ripple homeAlign">&#10004;Add New Shop</a>

<table class="w3-table w3-bordered w3-striped">
  <tr>
    <th>Shop No</th>
    <th>Shop Name</th>
    <th>Shop Address</th>
    <th>Shop Postal Code</th>
    <th>Shop Latitude</th>
    <th>Shop Longitude</th>
  </tr>
  <tr ng-repeat="shop in shops">
    <td>
     <button class="w3-btn w3-ripple" ng-click="editUser(user.id)">&#9998; Edit</button> 
    </td>
    <td>{{ $index + 1 }}</td>
    <td>{{ shop.shopName }}</td>
    <td>{{ shop.shopAddress }}</td>
    <td>{{ shop.shopPostCode }}</td>
    <td>{{ shop.latitude }}</td>
    <td>{{ shop.longitude }}</td>
  </tr>
</table>
<br>

</div>

<script type="text/javascript">
	
	var welcomeHome = angular.module("myApp", []);
	welcomeHome.controller('userCtrl', ['$scope', '$http', function($scope,$http) {

		$scope.shopsJson;
	
	var shopAllUrl = "/getAllShops";
	$http.get(shopAllUrl).success(function(response){
		$scope.shopsJson = response;
		
	}); 
	



</script>

</body>
</html>
