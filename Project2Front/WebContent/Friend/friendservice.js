/**
 * FriendService
 */

app.factory('FriendService',function($http){
	var friendService ={}
	
	var BASE_URL="http://localhost:8082/Project2back"
	
	friendService.listOfSuggestedUsers = function(){
		return $http.get(BASE_URL+"/getsuggestedusers")
	}
	
	friendService.friendRequest =function(toId){
		return $http.post(BASE_URL+"/friendrequest/"+toId)
	} 
	
	friendService.listOfPendingRequests=function(){
		return $http.get(BASE_URL+"/getAllPendingRequests")
	}
	
	friendService.acceptPendingRequest = function(name){
		return $http.put(BASE_URL+"/acceptpendingrequest/"+name)
	}
	friendService.denyPendingRequest = function(name){
		return $http.put(BASE_URL+"/denypendingrequest/"+name)
	}
	
	return friendService;
})