(function(ng){
    var mod = ng.module("moduloFacturas",["ui.router"]);
    mod.constant("facturaContext","api/facturas");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/facturas/";
           $urlRouterProvider.otherwise("/facturaList");
           
           $stateProvider.state('factura', {
                url: '/facturas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'facturas.html',
                        controller: 'facturaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state("facturasList",{
               url:'/list',
               parent:'factura',
               views:{
                   'listView':{
                       templateUrl: basePath + 'facturas.list.html'
                   }
               }
           });
           
    }]);
})(window.angular);

