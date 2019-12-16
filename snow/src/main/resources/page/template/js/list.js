define([], function () {
	return ["$scope","$state", function ($scope,$state) {
		
		$scope.findList = function(type){
			_get({
				url: "/quest/list",
				param: {
					type: type,
					openid: sessionStorage.getItem("openid")
				},
				callback: function (res) {
					console.log('请求到得数据：', res)
					$scope.qlist = res;
					$scope.$applyAsync();
				}
			})
		}

		$scope.findList("ACR")

		$scope.clickIndex = function(index){
			var dosm = document.getElementsByClassName("title-item");
			dosm[0].classList.remove('title-act');
			dosm[1].classList.remove('title-act');
			dosm[2].classList.remove('title-act');
			if(index == 0){
				dosm[0].classList.add('title-act');
				$scope.findList("ACR")
			}else if(index == 1){
				$scope.findList("RIF")
				dosm[1].classList.add('title-act');
			}else if(index == 2){
				$scope.findList("VCR")
				dosm[2].classList.add('title-act');
			}
		}
		$scope.goto = function(type,id){
			if(type == "ACR"){
				$state.go("acr",{id:id})
			}
			if(type == "VCR"){
				$state.go("vcr",{id:id})
			}
			if(type == "RIF"){
				$state.go("rif",{id:id})
			}
		}
	}];
});