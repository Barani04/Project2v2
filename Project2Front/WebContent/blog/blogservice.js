/**
 * 
 */

app.factory('BlogService',function($http){
	var blogService={}
	
	var BASE_URL="http://localhost:8083/Project2back"
	
	blogService.addBlog=function(blog){
		return $http.post(BASE_URL+"/saveblog",blog)
	}
	
	blogService.getBlogsWaitingForApproval=function(){
		return $http.get(BASE_URL+"/getblogs/"+0)
	}
	
	blogService.getBlogsApproved=function(){
		return $http.get(BASE_URL+"/getblogs/"+1)
	}
	
	blogService.getBlog=function(bid){
		return $http.get(BASE_URL+"/getblog/"+bid)
	}
	
	blogService.changeblogStatus=function(id){
		return $http.post(BASE_URL+"/approveblog/"+id)
	}
	
	blogService.addComment=function(blogComment){
		return $http.post(BASE_URL+"/addblogcomment",blogComment)
	}
	
	blogService.getBlogComments=function(bid){
		return $http.get(BASE_URL+"/getallblogcomments/"+bid)
	}
	
	blogService.getBlogbyUsername=function(userId){
		return $http.get(BASE_URL+"/getblogbyusername/"+userId)
	}
	
	return blogService;
})