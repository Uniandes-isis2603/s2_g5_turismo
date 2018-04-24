(function(ng){
    var mod = ng.module("pagoModule",["ui.router"]);
    mod.constant("pagoContext","api/pagos");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/pagos/";
           $urlRouterProvider.otherwise("/pagosList");
           
           $stateProvider.state('pago', {
                url: '/pago',
                abstract: true,
                views: {
                    'sideView': {
                        templateUrl: basePath + 'pagos.side.html'
                    },
                    'mainView': {
                        templateUrl: basePath + 'pagos.html',
                        controller: 'pagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state("pagosList",{
               url:'/list',
               parent:'pago',
               views:{
                   'listView':{
                       templateUrl: basePath + 'pagos.list.html'
                   }
                   ,'sideView': {
                        templateUrl: basePath + 'pagos.side.html'
                    }
               }
           });
           
    }]);
})(window.angular);

