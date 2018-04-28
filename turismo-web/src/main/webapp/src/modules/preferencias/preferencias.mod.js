/**
 * @ngdoc overview
 * @name preferencias.module:preferenciaModule
 * @description
 * Definición del módulo de Angular de Autores. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Autores 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los preferencias en la 
 * URL: 'localhost:8080/preferencias/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar preferencias), el controlador y la vista 
 * correspondiente.
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("preferenciaModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/preferencias/';
            // Mostrar la lista de preferencias será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/preferenciasList");
            // Definición del estado 'preferenciasList' donde se listan los preferencias
            $stateProvider.state('preferencias', {
                // Url que aparecerá en el browser
                url: '/preferencias',
                views: {
                    'sideViewPreferencias': {
                       templateUrl: basePath + 'preferencias.side.html'
                    },
                    'mainView': {
                        templateUrl: basePath + 'preferencias.html',
                        controller: 'preferenciaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
                }).state('preferenciasList', {
                url: '/list',
                parent: 'preferencias',
                views: {
                    'listView': {
                        templateUrl: basePath + 'preferencias.list.html'
                    },
                    'sideViewPreferencias': {
                        templateUrl: basePath + 'preferencias.side.html'
                    }
                }
                }).state('preferenciasCreate', {
                url: '/create/preferencias',
                parent: 'preferencias',
                views: {
                    'listView': {
                        templateUrl: basePath + '/new/preferencias.new.html',
                        controller: 'preferenciaNewCtrl'
                    }
                }
                }).state('preferenciaUpdate', {
                url: '/update/{preferenciaId:int}',
                parent: 'preferencias',
                param: {
                    preferenciaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/new/preferencias.new.html',
                        controller: 'preferenciaUpdateCtrl'
                    }
                }
                }).state('preferenciaDelete', {
                url: '/delete/{preferenciaId:int}',
                parent: 'preferencias',
                param: {
                    preferenciaId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + '/delete/preferencias.delete.html',
                        controller: 'preferenciaDeleteCtrl'
                    }
                }
        });
    }]);
})(window.angular);
