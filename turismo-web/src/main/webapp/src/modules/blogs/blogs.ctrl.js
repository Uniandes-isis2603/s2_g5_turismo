(function (ng) {
    var mod = ng.module("blogsModule");
    mod.constant("blogsContext", "api/blogs");
    mod.controller('blogsCtrl', ['$scope', '$http', 'blogsContext', '$state',
        /**
         * @ngdoc controller
         * @name blogs.controller:blogsCtrl
         * @description
         * Definición del controlador de Angular del módulo blogs. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} blogsContext Constante injectada que contiene la ruta
         * donde se encuentra el API de blogs en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, blogsContext, $state) {
            /**
             * @ngdoc function
             * @name getBlogs
             * @methodOf blogs.controller:blogsCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los blogs en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los blogs o API donde se puede consultar.
             */
            $http.get(blogsContext).then(function (response) {
                $scope.blogsRecords = response.data;
            });
        }
    ]);
}
)(window.angular);