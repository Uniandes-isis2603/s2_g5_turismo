(function(ng){
    var mod = ng.module("moduloUbicacion",["ui.router"]);
    mod.constant("ubicacionContext","api/Plans/10000/ubicaciones/");
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
               'params':{
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
                       templateUrl: basePath +'ubicacion.detail.html'
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
                   });;
           
    }]);
})(window.angular);

