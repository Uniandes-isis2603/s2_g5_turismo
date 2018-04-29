/**
 * @ngdoc overview
 * @name tarjetas.module:guiaModule
 * @description
 * Definición del módulo de Angular de Autores. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Autores 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los tarjetas en la 
 * URL: 'localhost:8080/tarjetas/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar tarjetas), el controlador y la vista 
 * correspondiente.
 */
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

