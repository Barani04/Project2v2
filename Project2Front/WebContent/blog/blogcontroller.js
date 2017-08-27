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
	
	$rootScope.getBlog=function(blog){
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
})