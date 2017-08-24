/**
 * 
 */

app.factory('UserService',function($http){
	var userService={};
	var BASE_URL="http://localhost:8081/Project2back"

	userService.getUser=function(){
		return $http.get(BASE_URL+"/getuser")
	}
	
	userService.updateUser=function(user){
		return $http.put(BASE_URL+"/updateuser",user)
	}
	userService.getUserAccWaitingForActivation=function(){
		return $http.get(BASE_URL+"/getusers/"+0)
	}
	
	userService.changeAccStatus=function(name){
		return $http.post(BASE_URL+"/activateuser/"+name)
	}
	
	return userService;
})