/**
 * Forum Service
 */

app.factory('ForumService',function($http){
	var forumService={}
	
	var BASE_URL="http://localhost:8082/Project2back"
	
	forumService.createForum=function(forum){
		return $http.post(BASE_URL+"/saveforum",forum)
	}
	
	forumService.getallForum=function(){
		return $http.get(BASE_URL+"/getallforum")
	}
	
	return forumService;
})