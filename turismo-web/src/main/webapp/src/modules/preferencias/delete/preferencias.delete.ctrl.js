(function (ng) {
    var mod = ng.module("preferenciaModule");
    mod.constant("preferenciasContext", "api/preferencias");
    mod.controller('preferenciaDeleteCtrl', ['$scope', '$http', 'preferenciasContext', '$state',
        /**
         * @ngdoc controller
         * @name preferencias.controller:preferenciaDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Preferencias. 
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
        function ($scope, $http, preferenciasContext, $state) {
            var idPreferencia = $state.params.preferenciaId;
            /**
             * @ngdoc function
             * @name deletePreferencia
             * @methodOf preferencias.controller:preferenciaDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la preferencia.
             * @param {String} id El ID de la preferencia a eliminar.
             */
            $scope.deletePreferencia = function () {
                $http.delete(preferenciasContext + '/' + idPreferencia, {}).then(function (response) {
                    $state.go('preferenciasList', {preferenciasId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);