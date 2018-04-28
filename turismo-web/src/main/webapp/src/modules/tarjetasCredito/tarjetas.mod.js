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
           }).state("tarjetaDetail",{
               url:'/{tarjetaId:int}/detail',
               parent: 'tarjeta',
               param:{
                   tarjetaId: null
               },
               views:{
                  'listView':{
                       templateUrl: basePath + 'tarjeta.list.html'
                   },
                   'sideView':{
                       templateUrl: basePath + 'tarjetas.side.html'
                   },
                   'detailView':{
                       templateUrl: basePath +'tarjeta.detail.html',
                       controller:"tarjetaDetailCtrl"
                   }
                   
               }
              
               
           }
                   ).state("tarjetaCreate",{
                       url:"/create",
                       parent:"tarjeta",
                       views:{
                           'detailView':{
                               templateUrl:basePath +"tarjetas.create.html",
                               controller:"tarjetaNewCtrl"
                           },
                           'sideView':{
                               templateUrl: basePath + 'tarjetas.side.html'
                           }
                       }
                   }).state('tarjetaUpdate', {
                url: '/update/{tarjetaId:int}',
                parent: 'tarjeta',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + "tarjetas.create.html",
                        controller: 'tarjetasUpdateCtrl'
                    },
                   'sideView':{
                       templateUrl: basePath + 'tarjetas.side.html'
                   }
                }
            });
           
    }]);
})(window.angular);

