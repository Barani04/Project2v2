/**
 * 
 */

app.controller('BlogController',function(BlogService,$scope,$location,$route,$rootScope,$cookieStore){
	
	
	
	BlogService.getBlogsApproved().then(function(response){
		$scope.blogsApproved = response.data;
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/login')
		}
	})
	
	$scope.addBlog=function(){
		BlogService.addBlog($scope.blog).then(function(response){
			console.log(response.status)
			Materialize.toast('Blog Created..!Waiting for approval', 3000);
			$location.path("/getallblogs")
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
			
			if(response.status==500){
				$scope.error=response.data
				$location.path('/addblog')
			}
			$location.path('/home')
		})
	}
	
	$scope.getBlog=function(blog){
		BlogService.getBlog(blog.bid).then(function(response){
			$rootScope.vb=response.data
			console.log($rootScope.vb)
			$cookieStore.put("vb",response.data)
			$location.path('/viewblog')
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		})
	}
	
	$scope.addComment=function(){
		
		$scope.blogComment.blog=$rootScope.vb
		console.log($scope.blogComment)
		BlogService.addComment($scope.blogComment).then(function(response){
			$scope.blogComment.commentText=''
			console.log(response.data)
			getBlogComments()
		},function(response){
			if(response.status==401){
				$location.path('/viewblog')
			}
		})
		
	}
	
	function getBlogComments(){
		BlogService.getBlogComments($rootScope.vb.bid).then(function(response){
			$scope.blogcomments = response.data
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/viewblog')
			}
		})
	}
	getBlogComments()
	
	
})