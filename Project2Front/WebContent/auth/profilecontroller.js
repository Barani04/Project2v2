/*
 *PROFILE PICTURE 
 */

app.controller('ProfileController',function(AuthService,$location,$scope){
	
	$scope.profile={}
	
	$scope.uploadProfiePic = function(){
		alert("Hai")
			AuthService.uploadProfiePic($scope.profile).then(function(response){
			$location.path('/home')
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		})
	}
	
});