/**
 * Forum Controller
 */

app.controller('ForumController',function(ForumService,$scope,$location){
	
	$scope.createForum=function(){
		ForumService.createForum($scope.forum).then(function(response){
			console.log(response.status)
			Materialize.toast('Forum Created..!', 3000);
			$location.path("/home")
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
			
			if(response.status==500){
				$scope.error=response.data
				$location.path('/createforum')
			}
			$location.path('/home')
		})
	}
	
	
		ForumService.getallForum().then(function(response){
			console.log(response.data)
			$scope.getallforum = response.data
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		})
		
})