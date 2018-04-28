(function (ng) {
    var mod = ng.module("comentariosModule");
    mod.constant("comentariosContext", "/comentarios");
    mod.controller('comentarioDeleteCtrl', ['$scope', '$http', 'comentariosContext', '$state',
        /**
         * @ngdoc controller
         * @name comentarios.controller:comentarioDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar comentarios. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} comentarioContext Constante injectada que contiene la ruta
         * donde se encuentra el API de comentarios en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, comentariosContext, $state) {
            var idComentario = $state.params.Id;
            /**
             * @ngdoc function
             * @name deletecomentario
             * @methodOf comentarios.controller:comentarioDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la comentario.
             * @param {String} id El ID de la comentario a eliminar.
             */
           
            $scope.deleteComentario = function () {
                $http.delete('api/blogs/'+ $state.params.blogId +comentariosContext + '/' + idComentario, {}).then(function (response) {
                    $state.go('comentariosList', {ComentarioId: response.data.idComentario}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);