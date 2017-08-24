/**
 * 
 */

app.controller('ReqController',function(UserService,BlogService,$scope,$rootScope,$location,$cookieStore,$route){
	$scope.user={};
	$scope.blog={};
	
	UserService.getUserAccWaitingForActivation().then(function(response){
		if(response.data.length==0){
			$scope.mes="No New User Requests for Approval...!"
		}
		$rootScope.ul=response.data.length;
		$cookieStore.put("ul",response.data.length)
		$scope.userRequests=response.data;
	},function(response){
			$scope.error=response.data
			$location.path('/login')
	})
	
	$scope.changeAccStatus=function(name){
		UserService.changeAccStatus(name).then(function(response){
			$scope.changeAccStatus=response.data;
			Materialize.toast('User Account Activated...!',2000);
			$route.reload();
		},function(response){
			$scope.error=response.data
			$location.path('/home')
		})
	}
	
	BlogService.getBlogsWaitingForApproval().then(function(response){
		if(response.data.length==0){
			$rootScope.mess="No New Blogs for Approval...!"
		}
		$rootScope.len=response.data.length;
		$cookieStore.put("len",response.data.length)
		$scope.blogsWaitingForApproval = response.data;
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/login')
		}
	})
	
	$scope.changeblogStatus=function(id){
		BlogService.changeblogStatus(id).then(function(response){
			$scope.changeblogStatus = response.data;
			Materialize.toast('Blog Approved..!',2000);
			$route.reload();
		},function(response){
			$scope.error=response.data
			$location.path('/home')
		})
		}
	
})