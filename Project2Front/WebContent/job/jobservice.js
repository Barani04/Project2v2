/**
 * 
 */

app.factory('JobService',function($http){
	var jobService={}
	var BASE_URL="http://localhost:8082/Project2back"
	jobService.saveJob=function(job){
		console.log(job)
		return $http.post(BASE_URL+"/savejob",job)
	}
	
	jobService.getAllJobs=function(){
		return $http.get(BASE_URL+"/getalljobs")
	}
	
	jobService.getJobDetails=function(jid){
		return $http.get(BASE_URL+"/getjobbyid/"+jid)
	}
	
	return jobService;
})