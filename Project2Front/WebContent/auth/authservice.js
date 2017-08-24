/**
 * 
 */

app.factory('AuthService',function($http){
	var authService={};
	var BASE_URL="http://localhost:8081/Project2back"
	
		authService.registerUser=function(user){
			return $http.post(BASE_URL+"/registeruser",user)
		}
		authService.validateUser=function(user){
			return $http.post(BASE_URL+"/validateuser",user)
		}
		
		authService.logout=function(){
			return $http.get(BASE_URL+"/logout")
		}
		return authService;
})