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
				console.log(response.data)
			}
		})
	}
	listOfSuggestedUsers()
})