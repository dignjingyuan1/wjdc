var API_HEADER = "http://api.bangwotrans.com:8082";
//const API_HEADER = "http://152.136.187.193:8082";
//const API_HEADER = "http://172.17.5.144:8082";
/**
 * get请求 
 * @param {Object} params
 */
function _get(params){
	params.type = 'GET';
	params.url = params.url + "?now=" + new Date().getTime();
	this._ajax(params);
}

/**
 * post请求
 * @param {Object} params
 */
function _post(params){
	params.type = 'POST';
	this._ajax(params);
}

/**
 * 添加loading
 */
function _loading(){
	var html = "<div class='loading-fixed'>"+
				"<div class='loading'>"+
					"<span></span>"+
			        "<span></span>"+
			        "<span></span>"+
			        "<span></span>"+
			        "<span></span>"+
			        "<span></span>"+
			        "<span></span>"+
			        "<span></span>"+
				"</div>"+
				"</div>";
	$(html).prependTo($("body"));
}

/**
 * 删除loading
 */
function _removeLoading(){
	$("body").find(".loading-fixed").remove();
}

/**
 * ajax
 * @param {Object} params
 */
function _ajax(params){
	var _token = localStorage.getItem("token");
	var _refreshToken = localStorage.getItem("refreshToken");
	var _validToken = localStorage.getItem("validToken");
	var cache = params.cache == false ? params.cache : true;
	var async = params.async == false ? params.async : true;
	var loading = params.loading == undefined ? true : params.loading;
	$.ajax({
		url: params.url,
		type: params.type,
		data: params.param ? params.param : {},
		dataType: 'json',
		timeOut: 15000,
		cache: cache, 
       	async: async,
		crossDomain:true,
		headers:{
			'token': _token,
			'refreshToken': _refreshToken,
			'validToken': _validToken
		},
		beforeSend: function(){
			loading ? _loading() : '';
		},
		success: function(res){
            params.callback(res);
		},
		complete: function(){
			loading ? _removeLoading() : '';
		},
		error: function(err){
			_removeLoading();
			params.callback(err);
		},
	});
}
function getQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}