(function(ng){
    var mod = ng.module("moduloFacturas");
    mod.controller("facturaCtrl",["$rootScope","$scope","$state","$stateParams","$http","facturaContext",
        /**
         * @ngdoc controller
         * @name facturas.controller:facturasCtrl
         * @description
         * Definición del controlador de Angular del módulo facturas. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} facturaContext Constante injectada que contiene la ruta
         * donde se encuentra el API de guias en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */

        
        
        function($rootScope,$scope,$state,$stateParams,$http,context)
        {
             /**
             * @ngdoc function
             * @name getfacturas
             * @methodOf facturas.controller:facturasCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran las facturas en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los guias o API donde se puede consultar.
             */
             
            //lista vacia
            $scope.records={};
            
            id=$rootScope.currentId;
            //carga las facturas de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
            });
            
            $http.get("api/usuario/"+id).then(function(responses){
                $scope.User = responses.data;
            });
          
           
            
            
}
    ]);
    }
 )(window.angular);


