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
		authService.uploadProfiePic=function(profile){
			return $http.post("http://localhost:8081/Project2back/uploadprofilepic",profile)
		}
		
		authService.logout=function(){
			return $http.get(BASE_URL+"/logout")
		}
		return authService;
})