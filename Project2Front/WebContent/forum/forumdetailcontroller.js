/**
 * Forum Detail Controller
 */

app.controller('ForumDetailController',function(ForumService,$location,$scope,$routeParams,$rootScope){
	var forumid = $routeParams.id
	
	$scope.forumpost={}
	
	$scope.getForumById = ForumService.getForumById(forumid).then(function(response){
		$scope.forumdetail = response.data
		console.log(response.data)
		isParticipant()
		listForumPosts()
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
	
	$scope.addForumPost = function(){
		$scope.forumpost.forid = forumid
		$scope.forumpost.postedBy = $rootScope.currentUser.username
		ForumService.addForumPost($scope.forumpost).then(function(response){
			console.log(response.data)
			console.log(response.status)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
	
	function listForumPosts(){
		ForumService.listForumPosts(forumid).then(function(response){
			$scope.listposts = response.data
			$scope.fplen = $scope.listposts.length
			console.log(response.data)
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/login')
			}
		})
	}
})