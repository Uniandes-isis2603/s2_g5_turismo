(function(ng){
    
    var mod = ng.module("valoracionesModule");
    mod.constant('valoracionesCreate',"api/valoraciones/10000/10002");
   mod.controller('valoracionesNewCtrl', ['$scope', '$http', 'valoracionesCreate', '$state', '$rootScope',
       
        function($scope, $http, valoracionesContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
            /**
         * @ngdoc controller
         * @name valoraciones.controller:valoracionesNewCtrl
         * @description
         * Definición del controlador auxiliar para crear valoraciones. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} valoracionesContext Constante injectada que contiene la ruta
         * donde se encuentra el API de valoraciones en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
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

