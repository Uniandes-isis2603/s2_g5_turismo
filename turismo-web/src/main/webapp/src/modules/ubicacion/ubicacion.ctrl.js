(function(ng){
    var mod = ng.module("moduloUbicacion");
    mod.controller("ubicacionCtrl",["$scope","$state","$stateParams","$http","ubicacionContext",
        
        
        
        function($scope,$state,$stateParams,$http,context)
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
            //carga las ubicacions de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
                 
            });
        }
    ]);
    }
 )(window.angular);


