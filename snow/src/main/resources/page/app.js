require.config({
    paths: {
        // angular
        "angular": "lib/components/angular/angular",
//      
        "angular-sanitize": "lib/angular-sanitize/angular-sanitize",
        
        // angular-ui
        "angular-ui-router": "lib/components/angular-ui-router/release/angular-ui-router",
        
        // angularAMD
        "angularAMD": "lib/components/angularAMD/angularAMD",
        
        "ngload": "lib/components/angularAMD/ngload"
    },
    shim: {
        // angular
		"angular": { exports: "angular" },
		
		"angular-sanitize": ["angular"],
        
        // angular-ui
        "angular-ui-router": ["angular"],
        
        // angularAMD
        "angularAMD": ["angular"],
        "ngload": ["angularAMD"]
    },
     waitSeconds: 0

});

define(["angular", "angularAMD", "angular-ui-router", "angular-sanitize"], function (angular, angularAMD) {
    
    let _ROUTER = undefined;
    // routes
    var registerRoutes = function($stateProvider, $urlRouterProvider,$locationProvider) {
        	
        // default
        $urlRouterProvider.otherwise("/home");
        
        // route
        var router = $stateProvider.state("home", angularAMD.route({
                url: "/home",
                templateUrl: "template/page/home.html",
                controllerUrl: "template/js/home.js"
        }))
        .state("login", angularAMD.route({
                url: "/login",
                templateUrl: "template/page/login.html",
                controllerUrl: "template/js/login.js"
        }))
        .state("statement", angularAMD.route({
                url: "/statement",
                templateUrl: "template/page/statement.html",
                controllerUrl: "template/js/statement.js"
        }))
        .state("reg", angularAMD.route({
                url: "/reg",
                templateUrl: "template/page/reg.html",
                controllerUrl: "template/js/reg.js"
        }))
        .state("door", angularAMD.route({
                url: "/door",
                templateUrl: "template/page/door.html",
                controllerUrl: "template/js/door.js"
        }))
        .state("list", angularAMD.route({
                url: "/list",
                templateUrl: "template/page/list.html",
                controllerUrl: "template/js/list.js"
        }))
        .state("acr", angularAMD.route({
                url: "/acr?:id",
                templateUrl: "template/page/acr.html",
                controllerUrl: "template/js/acr.js"
        }))
        .state("rif", angularAMD.route({
                url: "/rif?:id",
                templateUrl: "template/page/rif.html",
                controllerUrl: "template/js/rif.js"
        }))
        .state("vcr", angularAMD.route({
                url: "/vcr?:id",
                templateUrl: "template/page/vcr.html",
                controllerUrl: "template/js/vcr.js"
        }))
    };
        
    // module
    var app = angular.module("myApp", ["ngSanitize","ui.router"]);
    
    
    app.run(function ($rootScope,$state) {
//        console.log("app run")
       $rootScope.$on('$stateChangeStart',function(event){
           console.log("change");
           // TODO 监听操作是否有token没有去登陆
           if(!sessionStorage.getItem("openid")){
                $state.go("login")
           }
       });
    });

    // config
    app.config(["$stateProvider", "$urlRouterProvider",'$locationProvider', registerRoutes]);
   	
 	
   	
    return angularAMD.bootstrap(app);
});