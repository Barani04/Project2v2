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
	
	
	$scope.joinforum = function(){
		var userreq =$rootScpoe.currentUser.username
		ForumService.joinForum(userreq,forumId).then(function (response) {
			 $route.reload();
             Materialize.toast('Request to join the forum sent!', 3000);
		},function(response){
			
		})
	}
	
	
})