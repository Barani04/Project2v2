/**
 * Forum Detail Controller
 */

app.controller('ForumDetailController',function(ForumService,$location,$scope,$routeParams){
	var forumId = $routeParams.id
	$scope.getForumById = ForumService.getForumById(forumId).then(function(response){
		$scope.forumdetail = response.data
		console.log(response.data)
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/login')
		}
	})
	
	
})