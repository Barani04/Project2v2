/**
 * 
 */

var app=angular.module("myApp",['ngRoute','ngCookies'])
app.config(function($routeProvider) {
	$routeProvider
	.when('/home',{
		templateUrl:'views/home.html'
	})
	.when('/signup',{
		templateUrl:'auth/signup.html',
		controller:'AuthController'
	})
	.when('/login',{
		templateUrl:'auth/login.html',
		controller:'AuthController'
	})
	.when('/editprofile',{
		templateUrl:'user/updateprofile.html',
		controller:'UserController'
	})
	.when('/jobform',{
		templateUrl:'job/jobform.html',
		controller:'JobController'
	
	})
	
	.when('/getalljobs',{
		templateUrl:'job/jobspage.html',
		controller:'JobController'
	
	})
	
	.when('/addblog',{
		templateUrl:'blog/blogform.html',
		controller:'BlogController'
	
	})
	.when('/request/getallBlogs',{
		templateUrl:'blog/blogslist.html',
		controller:'ReqController'
	
	})
	
	.when('/viewblogs',{
		templateUrl:'blog/viewblogs.html',
		controller:'BlogController'
	
	})
	
	.when('/viewblog',{
		templateUrl:'blog/blogdisplay.html',
		controller:'BlogController'
	})
	
	.when('/request/useracc',{
		templateUrl:'requests/UserReq.html',
		controller:'ReqController'
	
	})
	
	.otherwise({
		templateUrl:'views/home.html'
	})
	
})
app.run(function(AuthService,$rootScope,$cookieStore,$location) {
	if($rootScope.currentUser==undefined){
		$rootScope.currentUser = $cookieStore.get("currentUser")
	}
	if($rootScope.len==undefined){
		$rootScope.len=$cookieStore.get("len")
	}
	
	if($rootScope.vb==undefined){
		$rootScope.vb=$cookieStore.get("vb")
	}
	
		$rootScope.logout=function(){
			AuthService.logout().then(function(response) {
			$rootScope.message='Logged Out Successfully'
				delete $rootScope.currentUser
				$cookieStore.remove("currentUser")
				$location.path('/login')
		},function(response){
			$scope.error=response.data
			$location.path('/login')
		})
	}
})