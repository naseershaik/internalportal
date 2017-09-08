//Define an angular module for our app

var sampleApp = angular.module("tekapp", ["ngRoute"]);
sampleApp.config(function($routeProvider) {
    $routeProvider
    .when("/Myallocation", {
        templateUrl : "./pages/myallocation.html",
		controller : "allocationController"
    })
	/*.when("/userLanding", {
        templateUrl : "userLandingPage.html",
		controller : "myCtrllr"
    })*/
	.when("/profile", {
        templateUrl : "./pages/profile.html",
		controller : "profileController"
    })
	.when("/updateProfile", {
        templateUrl : "./pages/updateProfile.html",
		controller : "profileController"
    })
});

sampleApp.controller('myCtrllr', function($scope,$rootScope) {
    $rootScope.myValue = true;
});
sampleApp.controller('profileController', function($scope,$rootScope) {
    $rootScope.myValue = false;
});


sampleApp.controller('allocationController', function($scope,$rootScope) {
	console.log("inside my controller");
	$scope.message = 'This is user My allocation page';
	$rootScope.myValue = false;
	
});


sampleApp.controller('ShowOrdersController', function($scope) {

	$scope.message = 'This is Show orders screen';

});
