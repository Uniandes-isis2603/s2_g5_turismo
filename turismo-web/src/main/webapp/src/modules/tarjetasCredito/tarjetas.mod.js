(function(ng){
    var mod = ng.module("moduloTarjetas",["ui.router"]);
    mod.constant("tarjetaContext","api/usuarios/10001/tarjetas");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/tarjetasCredito/";
           $urlRouterProvider.otherwise("/tarjetaList");
           
           $stateProvider.state('tarjeta', {
                url: '/tarjetas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjetas.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state("tarjetasList",{
               url:'/list',
               parent:'tarjeta',
               views:{
                   'listView':{
                       templateUrl: basePath + 'tarjeta.list.html'
                   },
                   'sideView':{
                       templateUrl: basePath + 'tarjetas.side.html'
                   }
               }
           });
           
    }]);
})(window.angular);

