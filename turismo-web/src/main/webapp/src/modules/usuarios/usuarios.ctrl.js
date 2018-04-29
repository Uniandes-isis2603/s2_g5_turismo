(function (ng) {
    var mod = ng.module("usuariosModule");
    mod.constant("usuariosContext", "api/usuario");
     
    mod.controller('usuariosCtrl', ['$scope', '$http', 'usuariosContext', '$state',
       /**
         * @ngdoc controller
         * @name usuarios.controller:usuariosCtrl
         * @description
         * Definición del controlador de Angular del módulo usuarios. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} usuariosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de usuarios en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
       
        function ($scope, $http, usuariosContext, $state) {
            /**
             * @ngdoc function
             * @name getUsuarios
             * @methodOf usuarios.controller:usuariosCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los usuarios en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los usuarios o API donde se puede consultar.
             */  
            alert("entro");
            $http.get(usuariosContext).then(function (response) {                
                $scope.usuariosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

