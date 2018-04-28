(function (ng) {
    var mod = ng.module("guiaModule");
    mod.constant("guiasContext", "api/guides");
    mod.controller('guiaUpdateCtrl', ['$scope', '$http', 'guiasContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name guias.controller:guiaUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar Guias. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} guiaContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Guias en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, guiasContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {};

            var idGuia = $state.params.guiaId;

            /**
             * @ngdoc function
             * @name getGuiaID
             * @methodOf guias.controller:guiaUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la guia por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de la guia o API donde se puede consultar.
             */
            $http.get(guiasContext + '/' + idGuia).then(function (response) {
                var guia = response.data;
                $scope.data.nombreGuia = guia.nombreGuia;
                $scope.data.idiomaGuia = guia.idiomaGuia;
            });

            /**
             * @ngdoc function
             * @name createGuia
             * @methodOf guias.controller:guiaUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar la guia 
             * por ID en formato JSON.
             * @param {String} id El ID de la guia a actualizar.
             * @param {Object} guia Objeto con la información nueva de la guia.
             */
            $scope.createGuia = function () {
                $http.put(guiasContext + "/" + idGuia, $scope.data).then(function (response) {
                    $state.go('guiasList', {guiaId: response.data.idGuia}, {reload: true});
                });
            }
        }]);
}
)(window.angular);