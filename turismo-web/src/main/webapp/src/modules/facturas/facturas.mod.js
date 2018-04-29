/**
 * @ngdoc overview
 * @name facturas.module:guiaModule
 * @description
 * Definición del módulo de Angular de Autores. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Autores 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los facturas en la 
 * URL: 'localhost:8080/facturas/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar facturas), el controlador y la vista 
 * correspondiente.
 */
(function(ng){
    var mod = ng.module("moduloFacturas",["ui.router"]);
    mod.constant("facturaContext","api/facturas");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/facturas/";
           $urlRouterProvider.otherwise("/facturasList");
           
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
                   },
                   'sideView':{
                       templateUrl: basePath +'facturas.side.html'
                   }
               }
           }).state("facturaDetail",{
               url:'/{facturaId:int}/detail',
               parent: 'factura',
               'params':{
                   facturaId: null
               },
               views:{
                  'listView':{
                       templateUrl: basePath + 'facturas.list.html'
                   },
                   'sideView':{
                       templateUrl: basePath + 'facturas.side.html'
                   },
                   'detailView':{
                       templateUrl: basePath +'facturas.detail.html',
                       controller:"facturaDetailCtrl"
                   }
                   
               }
              
               
           }
                   ).state("facturaCreate",{
                       url:"/create",
                       parent:"factura",
                       views:{
                           'detailView':{
                               templateUrl:basePath +"facturas.create.html",
                               controller:"facturaNewCtrl"
                           },
                   'sideView':{
                       templateUrl: basePath + 'facturas.side.html'
                   }
                       }
                   }).state('facturaUpdate', {
                url: '/update/{facturaId:int}',
                parent: 'factura',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + "facturas.create.html",
                        controller: 'facturasUpdateCtrl'
                    },
                   'sideView':{
                       templateUrl: basePath + 'facturas.side.html'
                   }
                }
            });
           
    }]);
})(window.angular);

