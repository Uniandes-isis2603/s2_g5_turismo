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
 * |                 |                            |                        |
 * | plansList       | /list                      | listView:              |
 * |                 |                            | plans.list.html        |
 * |                 |                            |                        |
 * | planDetail      | /{planId:int}/detail       | listView:              |
 * |                 |                            | plans.list.html        |
 * |                 |                            | detailView:            |
 * |                 |                            | plans.detail.html      |
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
                    'sideView': {
                        templateUrl: basePath + 'plans.side.html'
                    },
                    'mainView': {
                        templateUrl: basePath + 'plans.html',
                        controller: 'planCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                    
                }
                
            }).state('plansList', {
                url: '/list',
                parent: 'plans',
                views: {
                    'listView': {
                        templateUrl: basePath + 'plans.list.html'
                    },
                    'sideView': {
                        templateUrl: basePath + 'plans.side.html'
                    }
                }
            }).state('planDetail', {
                url: '/{planId:int}/detail',
                parent: 'plans',
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
                    'sideView': {
                        templateUrl: basePath + 'plans.side.html'
                    }
                    

                }

            });
        }]);
})(window.angular);
