(function (ng) {
    var mod = ng.module("blogsModule");
    mod.constant("blogsContext", "api/blogs");
    mod.controller('blogDeleteCtrl', ['$scope', '$http', 'blogsContext', '$state',
        /**
         * @ngdoc controller
         * @name blogs.controller:blogDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar blogs. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} blogContext Constante injectada que contiene la ruta
         * donde se encuentra el API de blogs en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, blogsContext, $state) {
            var idBlog = $state.params.blogId;
            /**
             * @ngdoc function
             * @name deleteblog
             * @methodOf blogs.controller:blogDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la blog.
             * @param {String} id El ID de la blog a eliminar.
             */
            $scope.deleteBlog = function () {
                $http.delete(blogsContext + '/' + idBlog, {}).then(function (response) {
                    $state.go('blogsList', {blogsId: response.data.idBlog}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);