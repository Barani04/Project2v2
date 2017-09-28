/**
 * 
 */

app.factory('JobService',function($http){
	var jobService={}
	var BASE_URL="http://localhost:8083/Project2back"
	jobService.saveJob=function(job){
		console.log(job)
		return $http.post(BASE_URL+"/savejob",job)
	}
	
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL+"/getalljobs")
	}
	
	jobService.getJobDetails=function(jobid){
		return $http.get(BASE_URL+"/getjobbyid/"+jobid)
	}
	
	jobService.applyJob=function(jobid){
		return $http.post(BASE_URL+"/applyjob/"+jobid)
	}
	
	jobService.isApplied=function(jobid){
		return $http.get(BASE_URL+"/getappjobs/"+jobid)
	}
	
	jobService.numOfApplicants = function(jobid){
		return $http.get(BASE_URL+"/applicantsnum/"+jobid)
	}
	jobService.getUserAppliedJobs = function(userId){
		return $http.get(BASE_URL+"/appliedjobs/"+userId)
	}
	return jobService;
})