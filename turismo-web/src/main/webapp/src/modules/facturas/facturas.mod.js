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
                       templateUrl: basePath +'facturas.detail.html'
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
                   });
           
    }]);
})(window.angular);

