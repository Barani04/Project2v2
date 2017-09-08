/*
 *PROFILE PICTURE 
 */

app.controller('ProfileController',function($scope,$http){
	$scope.image;
	
	$scope.uploadImage = function(){
		alert("hfbhsf")
		var fd = new FormData();
		var imgBlob = dataURItoBlob($scope.profile);
		fd.append('file',imgBlob);
		$http.post('http://localhost:8081/Project2back/uploadprofilepic',
				fd,{
				transformRequest:angular.identity,
				headers: {
					'Content-Type': undefined
				}
		})
		.success(function(response){
			console.log('success',response);
		})
		.error(function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		});
	}
	
	function dataURItoBlob(dataURI){
		var binary = atob(dataURI.split(',')[1]);
		var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
		var array=[];
		for(var i = 0;i < binary.length;i++){
			array.push(binary.charCodeAt(i));
		}
		return new Blob([new Uint8Array(array)],{
			type:mimeString
		});
	}
});

app.directive("fileread",[
	function(){
		return{
			scope: {
				fileread: "="
			},
			link: function(scope,element,attributes){
				element.bind("change",function(changeEvent){
					var reader = new FileReader();
					reader.onload = function(loadEvent){
						scope.$apply(function(){
							scope.fileread = loadEvent.target.result;
						});
					}
					reader.readAsDataURL(changeEvent.target.files[0]);
				});
			}
		}
	}
	]);

/*$scope.uploadFile = function(){
    var file = $scope.image;
    console.log('file is ' );
    console.dir(file);
    var uploadUrl = "http://localhost:8081/Project2back/uploadprofilepic";
    fileUpload.uploadFileToUrl(file, uploadUrl);
};
app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('file', $scope.image);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);*/
	
	/*$scope.uploadImage = function(){
		var fd = new FormData();
		var imgBlob = dataURItoBlob($scope.profile);
		fd.append('file',imgBlob);
		$http.post('http://localhost:8081/Project2back/uploadprofilepic',
				fd,{
				transformRequest:angular.identity,
				headers: {
					'Content-Type': undefined
				}
		})
		.then(function(response){
			console.log('success',response);
		},function(response){
			if(response.status==401){
				$scope.error=response.data
				$location.path('/home')
			}
		});
	}
	
	function dataURItoBlob(dataURI){
		var binary = atob(dataURI.split(',')[1]);
		var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];
		var array=[];
		for(var i = 0;i < binary.length;i++){
			array.push(binary.charCodeAt(i));
		}
		return new Blob([new Uint8Array(array)],{
			type:mimeString
		});
	}
	
	
});

app.directive("fileread",[
	function(){
		return{
			scope: {
				fileread: "="
			},
			link: function(scope,element,attributes){
				element.bind("change",function(changeEvent){
					var reader = new FileReader();
					reader.onload = function(loadEvent){
						scope.$apply(function(){
							scope.fileread = loadEvent.target.result;
						});
					}
					reader.readAsDataURL(changeEvent.target.files[0]);
				});
			}
		}
	}
	]);*/

/*$scope.profile={}

$scope.uploadProfiePic = function(){
	alert("Hai")
		AuthService.uploadProfiePic($scope.profile).then(function(response){
		$location.path('/home')
	},function(response){
		if(response.status==401){
			$scope.error=response.data
			$location.path('/home')
		}
	})
}*/