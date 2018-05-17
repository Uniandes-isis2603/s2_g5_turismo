/**
 * @ngdoc overview
 * @name plans.module:planModule
 * @description
 * Definición del módulo de Angular de Plans. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Plans 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los plans en la 
 * URL: 'localhost:8080/plans/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar plans), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO          | URL                        | VISTAS                 |
 * |-----------------|----------------------------|------------------------|
 * | plans           | /plans                     | mainView:              |
 * |                 |                            | plans.html             |
 * |                 |                            | plans.side.html        |
 * |                 |                            |                        |
 * | plansList       | /list                      | listView:              |
 * |                 |                            | plans.list.html        |
 * |                 |                            | plans.side.html        |
 * |                 |                            |                        |
 * | planDetail      | /{planId:int}/detail       | listView:              |
 * |                 |                            | plans.list.html        |
 * |                 |                            | detailView:            |
 * |                 |                            | plans.detail.html      |
 * |                 |                            | plans.side.html        |
 * |                 |                            |                        | 
 * |plansCreate      |/create                     | detailView:            |
 * |                 |                            | /plans.new.html        |
 * |planUpdate       |/{planId:int}/update        | detailView:            |
 * |                 |                            | /plans.new.html        |
 * |                 |                            |                        |
 * |planDelete       | /{planId:int}/delete       |detailView:             |
 * |                 |                            |/plans.delete.html      |
 * |-----------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("planModule", ['ui.router']);
    mod.constant("plansContext", "api/plans");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/plans/';
            $urlRouterProvider.otherwise("/plansList");

            $stateProvider.state('plans', {
                url: '/plans',
                abstract: true,
                views: {
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    },
                    'mainView': {
                        templateUrl: basePath + 'plans.html',
                        controller: 'planCtrl',
                        controllerAs: 'ctrl'
                    }   
                }
                 ,
                data: {
                    requireLogin: false,
                    roles: [true,false]
                }
            }).state('plansList', {
                url: '/list',
                parent: 'plans',
                views: {
                    'listView': {
                        templateUrl: basePath + 'plans.list.html'
                    },
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    }
                }
            }).state('planDetail', {
                url: '/{planId:int}/detail',
                parent: 'plansList',
                param: {
                    planId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'plans.list.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'plans.detail.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    }
                }
            }).state('plansCreate', {
                url: '/create/plan',
                parent: 'plans',
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/plans.new.html',
                        controller: 'planNewCtrl'
                    }
                }
            }).state('planUpdate', {
                url: '/update/{planId:int}',
                parent: 'plans',
                param: {
                    planId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/new/plans.new.html',
                        controller: 'planUpdateCtrl'
                    }
                }
            }).state('planDelete', {
                url: '/delete/{planId:int}',
                parent: 'plans',
                param: {
                    planId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + '/delete/plans.delete.html',
                        controller: 'planDeleteCtrl'
                    }
                }
            }).state('planDetailGuides',{
                url: '/guides',
                parent: 'planDetail',
                param:{
                    planId:null
                },
                views:{
                   'listView': {
                        templateUrl: basePath + 'plans.list.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'plans.detail.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    },
                    'planGuidesView':{
                        templateUrl: basePath + '/guias/plans.detail.guides.html',
                        controller: 'planDetailGuidesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                }).state('planDetailPreferencias',{
                url: '/preferences',
                parent: 'planDetail',
                param:{
                    planId:null
                },
                views:{
                   'listView': {
                        templateUrl: basePath + 'plans.list.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'plans.detail.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    },
                    'planGuidesView':{
                        templateUrl: basePath + '/categorias/plans.detail.preferencias.html',
                        controller: 'planDetailPreferenciasCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                }).state('planDetailUbicacion',{
                url: '/ubicacion/{lat:string}/{lon:string}',
                parent: 'planDetail',
                param:{
                    planId:null,
                    lat:null,
                    lon:null
                },
                views:{
                   'listView': {
                        templateUrl: basePath + 'plans.list.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'plans.detail.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    },
                    'planGuidesView':{
                        templateUrl: basePath + '/ubicacion/plans.detail.ubicacion.html',
                        controller: 'mapPlanCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                }).state('planDetailValoraciones',{
                url: '/valoraciones',
                parent: 'planDetail',
                param:{
                    planId:null
                },
                views:{
                   'listView': {
                        templateUrl: basePath + 'plans.list.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'plans.detail.html',
                        controller: 'planDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideViewPlan': {
                        templateUrl: basePath + 'plans.side.html'
                    },
                    'planGuidesView':{
                        templateUrl: basePath + '/valoraciones/plans.detail.valoraciones.html',
                        controller: 'planDetailValoracionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }]);
})(window.angular);
