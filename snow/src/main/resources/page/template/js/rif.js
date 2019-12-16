define([], function () {
    return ["$scope", "$state", function ($scope, $state) {
        $scope.qId = $state.params.id;
        if ($scope.qId) {
            _get({
                url: "/quest/getById",
                param: {
                    id: $scope.qId
                },
                callback: function (res) {
                    console.log("res:", res)
                    console.log(JSON.parse(res.content));
                    var obj = JSON.parse(res.content);
                    for (var key in obj) {
                        var bb = document.getElementsByName(key);
                        for (var i = 0; i < bb.length; i++) {
                            var dom = bb[i];
                            if (dom.type == "checkbox" || dom.type == "radio") {
                                if (dom.value != "on") {
                                    if (dom.value == obj[key]) {
                                        dom.checked = "checked"
                                    }
                                } else {
                                    dom.checked = "checked"
                                }
                            } else {
                                dom.value = obj[key];
                            }
                        }

                    }
                }
            })
        }
        $scope.submit = function () {
            var dudu = form2JsonString("acrForm")
            console.log(dudu)
            $scope.formData.age = $("#age").val();
            $scope.formData.nameAbc = $("#nameAbc").val();
            $scope.formData.sex = $("#sex").val();
            $scope.formData.openid = sessionStorage.getItem("openid");
            $scope.formData.type = "RIF";
            $scope.formData.content = dudu;
            _post({
                url: "/quest/insert",
                param: $scope.formData,
                callback: function (res) {
                    alert("提交成功！");
                    window.history.back(-1);
                }
            })
        }

        /** 表单序列化成json字符串的方法  */
        function form2JsonString(formId) {
            var paramArray = $('#' + formId).serializeArray();
            /*请求参数转json对象*/
            var jsonObj = {};
            $(paramArray).each(function () {
                jsonObj[this.name] = this.value;
            });
            console.log(jsonObj);
            // json对象再转换成json字符串
            return JSON.stringify(jsonObj);
        }
        $scope.formData = {
            age: '',
            content: '',
            createDate: new Date(),
            nameAbc: '',
            openid: '',
            type: ''
        }
    }];
});