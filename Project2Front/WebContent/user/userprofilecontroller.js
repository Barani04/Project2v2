/**
 * User Profile Controller
 */

app.controller('UserProfileController',function(UserService,BlogService,$scope,$location,$routeParams){
	$scope.profile = {};
	var userId = $routeParams.username
	
	$scope.getProfile = UserService.getProfile(userId).then(function(response){
			$scope.profile = response.data;
			console.log(response.data)
			getBlogbyUsername(userId)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		})
		
	function getBlogbyUsername(userId){
		BlogService.getBlogbyUsername(userId).then(function(response){
			$scope.blogPosts = response.data
			console.log(response.data)
			if(response.data.length < 1){
				$scope.post="No Blogs Posted Yet"
			}
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		})
	}
		
	
})