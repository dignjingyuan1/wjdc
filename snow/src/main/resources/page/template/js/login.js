define([], function () {
        return ["$scope", "$state", function ($scope, $state) {
            $scope.statement = function () {
                $state.go("statement");
            }
            $scope.goBack = function () {
                window.history.back(-1);
            }

            $scope.confirm = function(){
                var dom = document.getElementById("dudu")
                if (dom.checked == true) {
                    if(!$scope.phoneNumber){
                        alert("用戶名不能为空！")
                    }else if(!$scope.password){
                        alert("密码不能为空！")
                    }else{
                        // 去登陸
                        _get({
                            url: "/user/login",
                            param: {
                                phoneNumber: $scope.phoneNumber,
                                password: $scope.password
                            },
                            callback: function (res) {
                                console.log('登陸返回的用戶：', res)
                                if(res.openid){
                                    sessionStorage.setItem("openid",res.openid);
                                    $state.go("door")
                                }else{
                                    alert('用戶名密码不正确')
                                }
                            }
                        })
                    }
                } else {
                    alert("请勾选")
                }
            }
        }];
});