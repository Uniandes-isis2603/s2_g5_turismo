/**
 * @ngdoc overview
 * @name valoraciones.module:valoracionesModule
 * @description
 * Definición del módulo de Angular de valoraciones. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los valoraciones 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los usuarios en la 
 * URL: 'localhost:8080/valoraciones/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar valoraciones), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO          | URL                        | VISTAS                 |
 * |-----------------|----------------------------|------------------------|
 * | usuario         | /valoraciones              | mainView:              |
 * |                 |                            | valoraciones.html      |
 * |                 |                            |                        |
 * | valoracionesList| /list                      | listView:              |
 * |                 |                            | usuarios.list.html     |
 * |                 |                            |                        |
 * |-----------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("valoracionesModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/valoraciones/';
            // Mostrar la lista de guias será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/valoracionesList");
            // Definición del estado 'guiasList' donde se listan los guias
            $stateProvider.state('valoracion', {
                url: '/valoraciones',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'valoraciones.html',
                        controller: 'valoracionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }
                    ).state('valoracionesList', {
                // Url que aparecerá en el browser
                url: '/valoraciones/list',
                parent:'valoracion',
                views: {
                    'listView': {
                        templateUrl: basePath + 'valoraciones.list.html'
                        //controller: 'valoracionesCtrl',
                        //controllerAs: 'ctrl'
                    }
                }
            }).state('valoracionesCreate', {
                url: '/create/valoraciones',
                parent: 'valoracion',
                views: {
                    'listView': {
                        templateUrl: basePath + 'valoraciones.create.html',
                        controller: 'valoracionesNewCtrl'
                    }
                }
                }).state('valoracionesUpdate', {
                url: '/update/{valoracionesId:int}',
                parent: 'valoracion',
                param: {
                    valoracionesId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'valoraciones.new.html',
                        controller: 'valoracionesUpdateCtrl'
                    }
                }
                });
        }
    ]);
})(window.angular);