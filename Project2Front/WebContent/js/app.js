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
	
	.when('/chat',{
		templateUrl:'chat/chat.html',
		controller:'ChatController'
	})
	.when('/editprofile',{
		templateUrl:'user/updateprofile.html',
		controller:'UserController'
	})
	.when('/jobform',{
		templateUrl:'job/jobform.html',
		controller:'JobController'
	
	})
	
	.when('/getsuggestedusers',{
		templateUrl:'Friend/suggestedusers.html',
		controller:'FriendController'
	
	})
	
	.when('/pendingrequests',{
		templateUrl:'Friend/pendingrequests.html',
		controller:'FriendController'
	
	})
	
	.when('/getfriendlist',{
		templateUrl:'Friend/friendlist.html',
		controller:'FriendController'
	
	})
	
	.when('/profilepic',{
		templateUrl:'user/profilepic.html',
		controller:'UserController'
	})
	
	.when('/viewprofile/:username',{
		templateUrl:'user/userprofile.html',
		controller:'UserProfileController'
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
	
	.when('/viewblogbyid/:bid',{
		templateUrl:'blog/blogdisplay.html',
		controller:'BlogDetailController'
	})
	
	.when('/request/useracc',{
		templateUrl:'requests/UserReq.html',
		controller:'ReqController'
	
	})
	
	.when('/createforum',{
		templateUrl:'forum/createforum.html',
		controller:'ForumController'
	})
	
	.when('/getallforum',{
		templateUrl:'forum/getallforum.html',
		controller:'ForumController'
	})
	
	.when('/getforumbyid/:id',{
		templateUrl:'forum/viewforum.html',
		controller:'ForumDetailController'
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