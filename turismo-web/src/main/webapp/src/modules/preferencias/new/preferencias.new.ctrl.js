(function (ng) {
    var mod = ng.module("preferenciaModule");
    mod.constant("preferenciasContext", "api/preferences");
    mod.controller('preferenciaNewCtrl', ['$scope', '$http', 'preferenciasContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name preferencias.controller:preferenciaNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Preferencias. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} preferenciasContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Preferencias en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} booksContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Libros en el Backend.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, preferenciasContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf preferencias.controller:preferenciaNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el preferencia.
             * @param {Object} preferencia Objeto con el nuevo preferencia.
             */
            $scope.createPreferencia = function () {
                $http.post(preferenciasContext, $scope.data).then(function (response) {
                    $state.go('preferenciasList', {preferenciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);