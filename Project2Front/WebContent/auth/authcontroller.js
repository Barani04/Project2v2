/**
 * 
 */

app.controller('AuthController',function(AuthService,UserService,BlogService,ForumService,$scope,$rootScope,$location,$cookieStore){
	$scope.user={}
	
	$scope.registerUser=function(){
		AuthService.registerUser($scope.user).then(function(response) {
			$rootScope.notify='Registered Successfully...Wait for your Account Activation Mail'
			$location.path('/signup')	
		},function(response){
			console.log(response.status)
			$scope.error=response.data
			console.log(response.data)
			$location.path('/signup')
		})
	}
	
	$scope.validateUser=function(){
		AuthService.validateUser($scope.user).then(function(response) {
			console.log(response.data)
			$rootScope.currentUser=response.data
			$cookieStore.put("currentUser",response.data)
			Materialize.toast('Logged In Successfully..!',3000);
			$location.path('/home')
			
			UserService.getUserAccWaitingForActivation().then(function(response){
				$rootScope.ul=response.data.length;
				$cookieStore.put("ul",response.data.length)
			},function(response){
				$scope.error=response.data
				$location.path('/login')
		})
			
			BlogService.getBlogsWaitingForApproval().then(function(response){
				$rootScope.len=response.data.length;
				$cookieStore.put("len",response.data.length)
			},function(response){
				$scope.error = response.data
				console.log(response.status)
			})
			
			ForumService.getForumWaitingForApproval().then(function(response){
				$rootScope.forlen=response.data.length;
				$cookieStore.put("forlen",response.data.length)
			},function(response){
					$scope.error=response.data
					$location.path('/login')
			})
			
		},function(response){
			if(response.status==401 || response.status==500){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
			if(response.status==406){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
})