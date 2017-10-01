/**
 * Forum Service
 */

app.factory('ForumService',function($http){
	var forumService={}
	
	var BASE_URL="http://localhost:8083/Project2back"
	
	forumService.createForum=function(forum){
		return $http.post(BASE_URL+"/saveforum",forum)
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
	
	forumService.getForumApproved = function(){
		return $http.get(BASE_URL+"/getallforum/"+1)
	}
	forumService.getForumWaitingForApproval = function(){
		return $http.get(BASE_URL+"/getallforum/"+0)
	}
	
	forumService.changeForumStatus=function(id){
		return $http.post(BASE_URL+"/approveforum/"+id)
	}

	forumService.isParticipant=function(forumid){
		return $http.get(BASE_URL+"/isparticipant/"+forumid)
	}
	
	forumService.joinForum = function(forumid) {
		return $http.post(BASE_URL+"/joinforum/"+forumid)
	}
	
	forumService.addForumPost=function(forumpost){
		return $http.post(BASE_URL+"/add/forumpost",forumpost)
	}
	
	forumService.listForumPosts = function(forumid) {
		return $http.get(BASE_URL+"/list/forumpost/"+forumid)
	}
	
	forumService.getJoinRequests = function(){
		return  $http.get(BASE_URL+"/getjoinreq/"+0)
	}
	
	forumService.acceptJoinReq = function(reqid) {
		return $http.post(BASE_URL+"/acceptjoinreq/"+reqid) 
	}
	return forumService;
})