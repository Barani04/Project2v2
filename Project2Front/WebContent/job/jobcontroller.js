/**
 * 
 */

app.controller("JobController", function(JobService,$scope,$location) {
				/*	$scope.skill = ["C","C++","Java","HTML/CSS","Angular","Ruby","Python","Bootstrap","Sql","PHP"]*/
			
					$scope.exp=[1,2,3,4,5,6,7,8,9,10]
					
					$scope.showJobDetails=false;
			
				function getAllJobs(){
						JobService.getAllJobs().then(function(response){
							$scope.jobs=response.data;
						},function(response){
							$location.path("/login")
						})
					}	
					
					
					
					
				$scope.saveJob=function(){
				JobService.saveJob($scope.job).then(function(response){
					console.log(response.data)
					alert("Job Saved Successfully")
					console.log(response.status)
					$location.path('/getalljobs')
					
				},function(response){
					if(response.status==401){
						$scope.error=response.data
						$location.path('/home')
					}
					
				if(response.status==500){
					$scope.error=response.data
					$location.path('/savejob')
				}
					$location.path('/home')
						
				})
			}
			
			$scope.getJobDetails=function(jid){
				$scope.showJobDetails=true;
				JobService.getJobDetails(jid).then(function(response){
					$scope.job = response.data
				},function(response){
					console.log(response.data)
					$location.path('/login')
				})
			}
					
getAllJobs()
	})
		