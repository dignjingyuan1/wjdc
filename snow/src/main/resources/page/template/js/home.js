define([], function () {
    return ["$scope", "$state", function ($scope, $state) {
        $scope.join = function (text) {
           $state.go(text);
        }
    }];
});