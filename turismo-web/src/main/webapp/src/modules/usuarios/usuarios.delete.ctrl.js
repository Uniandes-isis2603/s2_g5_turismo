(function (ng) {
    var mod = ng.module("usuariosModule");
    mod.constant("usuariosContext", "api/usuario");
    mod.controller('usuariosDeleteCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        /**
         * @ngdoc controller
         * @name usuarios.controller:usuariosDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar usuarios. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} usuariosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Planes en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, usuariosContext, $state) {
            var idUsuarios = $state.params.usuarioId;
            /**
             * @ngdoc function
             * @name deleteUsuario
             * @methodOf usuarios.controller:usuariosDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la plan.
             * @param {String} id El ID del usuario a eliminar.
             */
            $scope.deleteUsuarios = function () {
                $http.delete(usuariosContext + '/' + idUsuarios, {}).then(function (response) {
                    $state.go('usuariosList', {usuariosId: response.data.idUsuarios}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);


