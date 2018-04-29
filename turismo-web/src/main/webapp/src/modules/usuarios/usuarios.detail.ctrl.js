(function (ng) {
    var mod = ng.module("usuariosModule");
    mod.constant("usuariosContext", "api/usuario");
    mod.controller('usuarioDetailCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        /**
         * @ngdoc controller
         * @name usuarios.controller:usuarioDetailCtrl
         * @description
         * Definición de un controlador auxiliar del módulo usuarios. 
         * Se crea el controlador con el cual se manejan las vistas de detalle
         * del módulo.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} plansContext Constante injectada que contiene la ruta
         * donde se encuentra el API de usuarios en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, usuariosContext, $state) {         
            /**
             * @ngdoc function
             * @name getUsuarioID
             * @methodOf usuario.controller:usuarioDetailCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra el usuario por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del usuario o API donde se puede consultar.
             */
            if (($state.params.usuarioId !== undefined)&& ($state.params.usuarioId !== null)) {
                $http.get(usuariosContext + '/' + $state.params.usuarioId).then(function (response) {
                    $scope.currentUsuario = response.data;
                });
            }
        }
    ]);
}
)(window.angular);
            