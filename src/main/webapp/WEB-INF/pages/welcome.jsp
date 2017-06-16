<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="demo">
	<head>
		<title>Hello Shop User</title>
		<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
	</head>

	<body>
		<div ng-controller="Hello">
			<p>${greeting}</p>
			<a href="<c:url value="/display_shop_form" />" class="w3-btn w3-ripple homeAlign">&#10004;Add Shops</a>
		</div>
		
		<script>
		
		var app = angular.module('demo', []);
		app.controller('Hello', function($scope, $http) {
		    $http.get('http://rest-service.guides.spring.io/greeting').
		        then(function(response) {
		            $scope.greetingMsg = response.data;
		        });
		});
		</script>
		
	</body>
</html>