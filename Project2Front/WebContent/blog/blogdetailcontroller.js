/**
 * 
 */

app.controller('BlogDetailController',function($scope,$location,$routeParams,BlogService){
	var id = $routeParams.bid

	$scope.getBlog = BlogService.getBlog(id).then(function(response){
			$scope.blogPost=response.data
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		})
		
$scope.addComment=function(){
		
		$scope.blogComment.blog=$scope.blogPost
		console.log($scope.blogComment)
		BlogService.addComment($scope.blogComment).then(function(response){
			$scope.blogComment.commentText=''
			console.log(response.data)
			getBlogComments()
		},function(response){
			if(response.status==401){
				$location.path('/viewblog')
			}
			$scope.error = response.data
			$location.path('/viewblogbyid/'+id)
		})
		
	}
	
	function getBlogComments(){
		BlogService.getBlogComments(id).then(function(response){
			$scope.blogcomments = response.data
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/viewblogbyid/'+id)
			}
		})
	}
	getBlogComments()
	
})