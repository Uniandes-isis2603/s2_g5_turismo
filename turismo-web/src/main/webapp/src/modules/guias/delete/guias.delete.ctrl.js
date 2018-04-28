(function (ng) {
    var mod = ng.module("guiaModule");
    mod.constant("guiasContext", "api/guides");
    mod.controller('guiaDeleteCtrl', ['$scope', '$http', 'guiasContext', '$state',
        /**
         * @ngdoc controller
         * @name guias.controller:guiaDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Guias. 
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
        function ($scope, $http, guiasContext, $state) {
            var idGuia = $state.params.guiaId;
            /**
             * @ngdoc function
             * @name deleteGuia
             * @methodOf guias.controller:guiaDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la guia.
             * @param {String} id El ID de la guia a eliminar.
             */
            $scope.deleteGuia = function () {
                $http.delete(guiasContext + '/' + idGuia, {}).then(function (response) {
                    $state.go('guiasList', {guiasId: response.data.idGuia}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);