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
            $stateProvider.state('valoracionesList', {
                // Url que aparecerá en el browser
                url: '/valoraciones/',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'valoraciones.list.html',
                        controller: 'valoracionesCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);