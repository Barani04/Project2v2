/**
 * 
 */

app.controller('AuthController',function(AuthService,UserService,BlogService,$scope,$rootScope,$location,$cookieStore){
	$scope.user={}
	$scope.profile={}
	
	$scope.registerUser=function(){
		AuthService.registerUser($scope.user).then(function(response) {
			$rootScope.notify='Registered Successfully...Wait for your Account Activation Mail'
			$location.path('/signup')	
		},function(response){
			console.log(response.status)
			console.log(response.data)
			$scope.error=response.data
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
			
		},function(response){
			if(response.status=401){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
			if(response.status=406){
				$scope.error = response.data
				console.log(response.status)
				$location.path('/login')
			}
		})
	}
	
})