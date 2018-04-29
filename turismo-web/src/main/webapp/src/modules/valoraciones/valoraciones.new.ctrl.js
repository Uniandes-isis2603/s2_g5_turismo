(function(ng){
    
    var mod = ng.module("valoracionesModule");
    mod.constant('valoracionesCreate',"api/valoraciones/10000/10002");
   mod.controller('valoracionesNewCtrl', ['$scope', '$http', 'valoracionesCreate', '$state', '$rootScope',
       
        function($scope, $http, valoracionesContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
           
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

