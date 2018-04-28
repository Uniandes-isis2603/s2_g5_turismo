(function (ng) {
    var mod = ng.module("guiaModule");
    mod.constant("guiasContext", "api/guides");
    mod.controller('guiaNewCtrl', ['$scope', '$http', 'guiasContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name guias.controller:guiaNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Guias. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} guiasContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Guias en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} booksContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Libros en el Backend.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, guiasContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf guias.controller:guiaNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el guia.
             * @param {Object} guia Objeto con el nuevo guia.
             */
            $scope.createGuia = function () {
                $http.post(guiasContext, $scope.data).then(function (response) {
                    $state.go('guiasList', {guiaId: response.data.idGuia}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);