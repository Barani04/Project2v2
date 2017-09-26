/**
 * Forum Service
 */

app.factory('ForumService',function($http){
	var forumService={}
	
	var BASE_URL="http://localhost:8083/Project2back"
	
	forumService.createForum=function(forum){
		return $http.post(BASE_URL+"/saveforum",forum)
	}
	
	forumService.getallForum=function(){
		return $http.get(BASE_URL+"/getallforum")
	}
	
	forumService.getForumById=function(id){
		return $http.get(BASE_URL+"/getforumbyid/"+id)
	}
	
	forumService.getForumPosts=function(id){
		return $http.get(BASE_URL+"/viewforumposts/"+id)
	}
	
	forumService.getForumpost = function(fpid){
		return $http.get(BASE_URL+"/viewforumpost/"+fpid)
	}
	
	forumService.addForumPost = function(forumpost){
		return $http.post(BASE_URL+"/addforumpost",forumpost)
	}
	
	return forumService;
})