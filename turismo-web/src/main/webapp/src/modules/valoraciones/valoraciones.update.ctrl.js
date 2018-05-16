(function(ng){
    var mod = ng.module("valoracionesModule");
    mod.controller("valoracionesUpdateCtrl",["$scope","$rootScope","$http","valoracionesContext","$state","$filter",
    
    
    
    
    /**
         * @ngdoc controller
         * @name valoraciones.controller:valoracionesUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar valoraciones. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} valoracionesContext Constante injectada que contiene la ruta
         * donde se encuentra el API de valoraciones en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
    function ($scope, $rootScope, $http, valoracionesContext, $state, $filter) {
            $rootScope.edit = true;
            
           
            id = $state.params.valoracionesId;
            
           if ($state.params.usuarioId !== null && $state.params.usuarioId !== undefined) 
           {  
               /**
             * @ngdoc function
             * @name getValoracionID
             * @methodOf valoraciones.controller:valoracionesUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la valoracion por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del valoraciones o API donde se puede consultar.
             */ 
            $http.get(valoracionesContext+"/"+id).then(function(response){
                var valoracion = response.data;
                $scope.valoracionesId = valoracion.id;
                $scope.valoracionesCalificacion = valoracion.calificacion;
                $scope.valoracionesComentario = valoracion.comentario;
                
            });
        }
    
    
            /**
             * @ngdoc function
             * @name createValoraciones
             * @methodOf valoraciones.controller:valoracionesUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar la valoracion 
             * por ID en formato JSON.
             * @param {String} id El ID de la valoracion a actualizar.
             * @param {Object} plan Objeto con la información nueva de la valoracion.
             */
            $scope.createValoraciones = function () {
                $http.post(valoracionesContext, { 
                    id:$scope.valoracionesId,
                    calificacion:$scope.valoracionesCalificacion,
                    comentario:$scope.valoracionesComentario
               }).then(function (response) {
                    $state.go('valoracionesList', {valoracionesId: response.data.id}, {reload: true});
                });
            };
        }    
    ]);
})(window.angular);

