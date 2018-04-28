(function (ng) {
    var mod = ng.module("preferenciaModule");
    mod.constant("preferenciasContext", "api/preferences");
    mod.controller('preferenciaUpdateCtrl', ['$scope', '$http', 'preferenciasContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name preferencias.controller:preferenciaUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar Preferencias. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} preferenciaContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Preferencias en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, preferenciasContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            var idPreferencia = $state.params.preferenciaId;

            /**
             * @ngdoc function
             * @name getPreferenciaID
             * @methodOf preferencias.controller:preferenciaUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la preferencia por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de la preferencia o API donde se puede consultar.
             */
            $http.get(preferenciasContext + '/' + idPreferencia).then(function (response) {
                var preferencia = response.data;
                $scope.data.tipoPlan = preferencia.tipoPlan;
            });

            /**
             * @ngdoc function
             * @name createPreferencia
             * @methodOf preferencias.controller:preferenciaUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar la preferencia 
             * por ID en formato JSON.
             * @param {String} id El ID de la preferencia a actualizar.
             * @param {Object} preferencia Objeto con la información nueva de la preferencia.
             */
            $scope.createPreferencia = function () {
                $http.put(preferenciasContext + "/" + idPreferencia, $scope.data).then(function (response) {
                    $state.go('preferenciasList', {preferenciaId: response.data.id}, {reload: true});
                });
            }
        }]);
}
)(window.angular);