define([], function () {
	return ["$scope","$state", function ($scope,$state) {
//		$state.go();
		$scope.goback = function(){
			window.history.back(-1); 
		}
	}];
});