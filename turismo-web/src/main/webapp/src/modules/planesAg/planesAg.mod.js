(function(ng){
    var mod = ng.module("miPlanModule",["ui.router"]);
    mod.constant("miPlanContext","api/miPlan");
    mod.config(["$stateProvider","$urlRouterProvider",function($stateProvider,$urlRouterProvider){
           var basePath="src/modules/planesAg/";
           $urlRouterProvider.otherwise("/miPlanList");
           
           $stateProvider.state('miPlan', {
                url: '/planesAg',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'planesAg.html',
                        controller: 'miPlanCtrl',
                        controllerAs: 'ctrl'
                    }
                    ,
                    'sideView': {
                        templateUrl: basePath + 'planesAg.side.html'
                    }
                }
            }).state("miPlanList",{
               url:'/list',
               parent:'miPlan',
               views:{
                   'listView':{
                       templateUrl: basePath + 'planesAg.list.html'
                   }
                   ,
                    'sideView': {
                        templateUrl: basePath + 'planesAg.side.html'
                    }
               }
           }).state("miPlanDetail",{
               url:'/{miPlanId:int}/detail',
               parent:'miPlan',
               param:{ miPlanId :null},
               views:{
                   'listView': {
                        templateUrl: basePath + 'planesAg.list.html',
                        controller: 'miPlanDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'planesAg.detail.html',
                        controller: 'miPlanDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideView': {
                        templateUrl: basePath + 'planesAg.side.html'
                    }
               }
           }).state("miPlanCreate",{
               url:'/create',
               parent:'miPlan',
               views:{
                   'listView':{
                       templateUrl: basePath + '/new/planesAg.new.html',
                       controller: 'miPlanNewCtrl'
                   }
                   ,'sideView': {
                        templateUrl: basePath + 'planesAg.side.html'
                    }
               }
           }).state("miPlanUpdate",{
               url:'/update/{miPlanId:int}',
               parent:'miPlan',
               param:{
                    miPlanId: null
                },
               views:{
                   'listView':{
                       templateUrl: basePath + '/new/planesAg.new.html',
                       controller: 'miPlanUpdateCtrl'
                   }
                   ,'sideView': {
                        templateUrl: basePath + 'planesAg.side.html'
                    }
               }
           }).state('miPlanDelete', {
                url: '/delete/{miPlanId:int}',
                parent: 'miPlan',
                param: {
                    miPlanId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/delete/planesAg.delete.html',
                        controller: 'miPlanDeleteCtrl'
                    }
                }
            });
           
    }]);
})(window.angular);



