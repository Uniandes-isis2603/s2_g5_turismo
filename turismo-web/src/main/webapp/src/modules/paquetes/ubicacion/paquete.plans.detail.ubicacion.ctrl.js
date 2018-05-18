
(function (ng) {
    var mod = ng.module("uiGmapgoogle-maps");
    mod.constant("plansContext", "api/plans");
    mod.controller('mapUbicacionCtrl', ['$scope', '$http', 'plansContext', '$state',
        /**
         * @ngdoc controller
         * @name plans.controller:planDetailCtrl
         * @description
         * Definición de un controlador auxiliar del módulo plans. 
         * Se crea el controlador con el cual se manejan las vistas de detalle
         * del módulo.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} plansContext Constante injectada que contiene la ruta
         * donde se encuentra el API de plans en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, plansContext, $state) {
            //Valor para inicializar un mapa (no se porque no funciona si no se hace esto :C)
            $scope.map = {center: {latitude: 45, longitude: -73}, zoom: 13};
         
            $scope.marker=[];
           
            
            
            $scope.init = function ()
            {
                $scope.map.zoom = 1;
            
                
            $scope.marker={id: $state.params.paqueteId,
                          coords: {
                                  latitude:  $state.params.lat,
                                  longitude: $state.params.lon
                                  },
                       options: { draggable: false }};
            
         };
            
        }]);
}
)(window.angular);