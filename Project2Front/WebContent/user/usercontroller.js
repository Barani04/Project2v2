/**
 * 
 */
app.controller('UserController',function(UserService,$scope,$location,$rootScope,$cookieStore){
	$scope.user={}
	if($rootScope.currentUser!=undefined){
		UserService.getUser().then(function(response){
		$scope.user=response.data;
	},function(response){
		console.log(response.status)
		$location.path('/editprofile')
	})
	}
	
	$scope.updateUser=function(){
		UserService.updateUser($scope.user).then(function(response){
			alert("Updated Successfully")
			$location.path('/home')
		},function(response){
			console.log(response.data)
			$scope.error=response.data
			if(response.status==401)
				$location.path('/login')
			$location.path('/editprofile')
		})
	}
	
})