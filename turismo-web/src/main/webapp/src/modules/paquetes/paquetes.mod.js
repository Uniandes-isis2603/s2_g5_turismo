(function(ng){
    var mod = ng.module("paqueteModule",["ui.router"]);
    mod.constant("paqueteContext","api/paquetes");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/paquetes/";
           $urlRouterProvider.otherwise("/paquetesList");
           
           $stateProvider.state('paquete', {
                url: '/paquete',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'paquete.html',
                        controller: 'paqueteCtrl',
                        controllerAs: 'ctrl'
                    }
                    ,
                    'sideView': {
                        templateUrl: basePath + 'paquete.side.html'
                    }
                }
            }).state("paquetesList",{
               url:'/list',
               parent:'paquete',
               views:{
                   'listView':{
                       templateUrl: basePath + 'paquetes.list.html'
                   }
                   ,
                    'sideView': {
                        templateUrl: basePath + 'paquete.side.html'
                    }
               }
           }).state("paqueteDetail",{
               url:'/{paqueteId:int}/detail',
               parent:'paquete',
               param:{ paqueteId :null},
               views:{
                   'listView': {
                        templateUrl: basePath + 'paquetes.list.html',
                        controller: 'paqueteDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'paquete.detail.html',
                        controller: 'paqueteDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideView': {
                        templateUrl: basePath + 'paquete.side.html'
                    }
               }
           }).state("paqueteCreate",{
               url:'/create',
               parent:'paquete',
               views:{
                   'listView':{
                       templateUrl: basePath + '/new/paquete.new.html',
                       controller: 'paqueteNewCtrl'
                   }
                   ,'sideView': {
                        templateUrl: basePath + 'paquete.side.html'
                    }
               }
           }).state("paqueteUpdate",{
                url:'/update/{paqueteId:int}',
                parent:'paquete',
                param:{
                    paqueteId: null
                },
            views:{'listView':{
                       templateUrl: basePath + '/new/paquete.new.html',
                       controller: 'pagoUpdateCtrl'
                   }
                   ,'sideView': {
                        templateUrl: basePath + 'pagos.side.html'
                    }
                }
            }).state('paqueteDelete', {
                url: '/delete/{paqueteId:int}',
                parent: 'paquete',
                param: {
                    paqueteId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/delete/paquete.delete.html',
                        controller: 'paqueteDeleteCtrl'
                    }
                }
            });
           
    }]);
})(window.angular);


