/**
 * Forum Detail Controller
 */

app.controller('ForumDetailController',function(ForumService,$location,$scope,$routeParams){
	var forumid = $routeParams.id
	$scope.getForumById = ForumService.getForumById(forumid).then(function(response){
		$scope.forumdetail = response.data
		console.log(response.data)
		isParticipant()
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/home')
		}
	})
	
	function isParticipant(){
		ForumService.isParticipant(forumid).then(function(response){
			$scope.isparticipant =false
			if(response.data ==""){
				$scope.isparticipant = true
			}
			console.log($scope.isparticipant)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
	
	$scope.joinForum = function(){
		ForumService.joinForum(forumid).then(function(response) {
			console.log(response.status)
			isParticipant()
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
})