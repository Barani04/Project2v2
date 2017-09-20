/**
 * Chat Service
 */

app.filter('reverse',function(){
	return function(items){
		return items.slice().reverse();
	};
});

app.directive('ngFocus',function(){
	return function(scope,element,attrs) {
		element.bind('click',function(){
			$('.'+attrs.ngFocus)[0].focus();
		});
	};
});

app.factory('socket',function($rootScope){
	Materialize.toast('app Factory',2000)
	var socket = new SockJS('/Project2back/portfolio');
	var stompClient = Stomp.over(socket);
	stompClient.connect('','',function(frame){
		$rootScope.$broadcast('sockConnected',frame);
	});
	
	return{
		stompClient: stompClient
	};
});