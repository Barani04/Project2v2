/**
 * FriendController
 */

app.controller('FriendController', function($scope, $location, FriendService) {
	function listOfSuggestedUsers() {
		FriendService.listOfSuggestedUsers().then(function(response) {
			$scope.suggestedusers = response.data
		}, function(response) {
			if (response.status == 401) {
				$location.path('/login')
				console.log(response.status)
			}
		})
	}
	
	function listOfPendingRequests(){
		FriendService.listOfPendingRequests().then(function(response){
			$scope.pendingRequests = response.data;
		},function(response){
			if (response.status == 401) {
				$location.path('/login')
				console.log(response.status)
			}
		})
	}
	
	
	$scope.friendRequest = function(toId){
		FriendService.friendRequest(toId).then(function(response){
			Materialize.toast("Friend Request Sent...!",3000)
			listOfSuggestedUsers();
			$location.path('/suggestedussers')
		},function(response){
			if (response.status == 401) {
				$location.path('/login')
				console.log(response.status)
			}
		})
	}
	
	$scope.updatePendingRequest = function(name,status){
		/*
		 * pendingRequest.status='P'
		 * pendingRequest.status = status => pendingRequest.status='A' or 'D'
		 */
		/*console.log(pendingRequest.status)
		pendingRequest.status=status*/
		/*
		 *pendingRequest is a Friend Object with
		 *id,fromId,toId and status(A/D) 
		 */
		if(status =='A'){
			FriendService.acceptPendingRequest(name).then(function(response){
				listOfPendingRequests()
				$location.path('/pendingrequests')
			},function(response){
				if (response.status == 401) {
					$location.path('/login')
					console.log(response.status)
				}
			})
		}
		if(status == 'D'){
			FriendService.denyPendingRequest(name).then(function(response){
				listOfPendingRequests()
				$location.path('/pendingrequests')
			},function(response){
				if (response.status == 401) {
					$location.path('/login')
					console.log(response.status)
				}
			})
		}
	}
	
	function getFriendList(){
		FriendService.getFriendList().then(function(response){
			$scope.friends = response.data;
		},function(response){
			if (response.status == 401) {
				$location.path('/login')
				console.log(response.status)
			}
		})
	}
	
	getFriendList()
	listOfPendingRequests()
	listOfSuggestedUsers()
})