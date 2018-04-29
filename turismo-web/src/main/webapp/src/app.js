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
        'usuariosModule'
        //'valoracionesModule'
       
        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
})(window.angular);
