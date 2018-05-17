(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.controller("tarjetaCtrl",["$rootScope","$scope","$state","$stateParams","$http","tarjetaContext",
               /**
         * @ngdoc controller
         * @name ubicacion.controller:ubicacionCtrl
         * @description
         * Definición del controlador de Angular del módulo ubicacion. 
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
             * @name getubicacion
             * @methodOf ubicacion.controller:ubicacionCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran las ubicacion en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los guias o API donde se puede consultar. 
             **/
            //lista vacia
            $scope.records={};           
            $scope.id=$rootScope.currentId;
            debugger;
            
            //carga las ubicacion de un usuario
            $http.get("api/usuarios/"+$scope.id+"/tarjetas").then(function(response){
                $scope.records = response.data;
                 
            });
            

          }

     ]);
 }
 )(window.angular);


