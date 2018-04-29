(function(ng){
    var mod = ng.module("valoracionesModule");
    mod.controller("valoracionesUpdateCtrl",["$scope","$rootScope","$http","valoracionesContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, valoracionesContext, $state, $filter) {
            $rootScope.edit = true;
            
           
            id = $state.params.valoracionesId;
            
           if ($state.params.usuarioId !== null && $state.params.usuarioId !== undefined) 
           {  
            $http.get(valoracionesContext+"/"+id).then(function(response){
                var valoracion = response.data;
                $scope.valoracionesId = valoracion.id;
                $scope.valoracionesCalificacion = valoracion.calificacion;
                $scope.valoracionesComentario = valoracion.comentario;
                
            });
        }
    
    
           
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

