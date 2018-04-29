/**
 * @ngdoc overview
 * @name ubicacion.module:guiaModule
 * @description
 * Definición del módulo de Angular de Autores. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Autores 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los ubicacion en la 
 * URL: 'localhost:8080/ubicacion/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar ubicacion), el controlador y la vista 
 * correspondiente.
 */
(function(ng){
    var mod = ng.module("moduloUbicacion",["ui.router"]);
    mod.constant("ubicacionContext","api/Plans/10000/ubicaciones");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/ubicacion/";
           $urlRouterProvider.otherwise("/ubicacionList");
           
           $stateProvider.state('ubicacion', {
                url: '/ubicacion',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ubicacion.html',
                        controller: 'ubicacionCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state("ubicacionList",{
               url:'/list',
               parent:'ubicacion',
               views:{
                   'listView':{
                       templateUrl: basePath + 'ubicacion.list.html'
                   },
                   'sideView':{
                       templateUrl: basePath + 'ubicacion.side.html'
                   }
               }
           }).state("ubicacionDetail",{
               url:'/{ubicacionId:int}/detail',
               parent: 'ubicacion',
               param:{
                   ubicacionId: null
               },
               views:{
                  'listView':{
                       templateUrl: basePath + 'ubicacion.list.html'
                   },
                   'sideView':{
                       templateUrl: basePath + 'ubicacion.side.html'
                   },
                   'detailView':{
                       templateUrl: basePath +'ubicacion.detail.html',
                       controller:"ubicacionDetailCtrl"
                   }
                   
               }
              
               
           }
                   ).state("ubicacionCreate",{
                       url:"/create",
                       parent:"ubicacion",
                       views:{
                           'detailView':{
                               templateUrl:basePath +"ubicacion.create.html",
                               controller:"ubicacionNewCtrl"
                           },
                   'sideView':{
                       templateUrl: basePath + 'ubicacion.side.html'
                   }
                       }
                   }).state('ubicacionUpdate', {
                url: '/update/{ubicacionId:int}',
                parent: 'ubicacion',
                param: {
                    ubicacionId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + "ubicacion.create.html",
                        controller: 'ubicacionUpdateCtrl'
                    },
                   'sideView':{
                       templateUrl: basePath + 'ubicacion.side.html'
                   }
                }
            });
           
    }]);
})(window.angular);

