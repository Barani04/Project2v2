/**
 * Job Details Controller
 */

app.controller("JobDetailController", function(JobService, $scope,$routeParams, $location,$rootScope) {
	var jobid = $routeParams.jid

	$scope.getJobDetails = JobService.getJobDetails(jobid).then(
			function(response) {
				isApplied()
				numOfApplicants()
				$scope.job = response.data
			}, function(response) {
				console.log(response.data)
				$location.path('/home')
			})

	$scope.applyJob = function() {

		JobService.applyJob(jobid).then(function(response) {

			console.log(response.status)
			isApplied()
			numOfApplicants()
		}, function(response) {
			console.log(response.data)
			console.log(response.status)
			$location.path('/home')
		})
	}
	
	function isApplied(){
		JobService.isApplied(jobid).then(function(response){
			$scope.isapplied = response.data
			$scope.applied=false
			if($scope.isapplied==""){
				$scope.applied=true
			}
			
			console.log($scope.applied)
			console.log(response.data)
		},function(response){
			console.log(response.data)
			console.log(response.status)
			$location.path('/home')
		})
	}
	
	function numOfApplicants(){
		JobService.numOfApplicants(jobid).then(function(response){
			$scope.count = response.data
			console.log(response.data)
		},function(response){
			console.log(response.data)
			console.log(response.status)
			$location.path('/home')
		})
	}
})
