/**
 * @ngdoc overview
 * @name guias.module:guiaModule
 * @description
 * Definición del módulo de Angular de Autores. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Autores 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los guias en la 
 * URL: 'localhost:8080/guias/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar guias), el controlador y la vista 
 * correspondiente.
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("guiaModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/guias/';
            // Mostrar la lista de guias será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/guiasList");
            // Definición del estado 'guiasList' donde se listan los guias
            $stateProvider.state('guiasList', {
                // Url que aparecerá en el browser
                url: '/guias/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'guias.list.html',
                        controller: 'guiaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
