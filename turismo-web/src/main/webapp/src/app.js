(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        'ui.bootstrap',
        // Internal modules dependencies  
        'miPlanModule',
        'pagoModule',
        'planModule',
        'preferenciaModule',
        'moduloTarjetas',
        'guiaModule',
        'blogsModule',
        'moduloFacturas',
        'moduloUbicacion',
        'paqueteModule',
        'comentariosModule',
        'usuariosModule',
        'valoracionesModule',
        'uiGmapgoogle-maps'
    ]);
    app.run(function($rootScope) {
    $rootScope.lat = 10;
    $rootScope.lon = 10;
});
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
