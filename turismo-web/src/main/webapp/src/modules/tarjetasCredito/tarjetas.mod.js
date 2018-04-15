(function(ng){
    var mod = ng.module("moduloTarjetas",["ui.router"]);
    mod.constant("tarjetaContext","api/usuarios/{usuarioid}/tarjetas");
    mod.constant("tarjetaContext","api/usuarios/{usuarioid}/tarjetas");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/tarjetasCredito/";
           $urlRouterProvider.otherwise("/tarjetaList");
           
           $stateProvider.state("tarjetaList",{
               url:'/tarjetas',
               views:{
                   'mainView':{
                       controller:'citiesCtrl',
                       controllerAs:"Ctrl",
                       templateUrl: basePath + 'tarjetas.list.html'
                   }
               }
           })
           
    }])
})(window.angular);

