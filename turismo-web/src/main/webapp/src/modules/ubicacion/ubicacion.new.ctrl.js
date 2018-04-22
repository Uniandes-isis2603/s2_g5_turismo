(function(ng){
    
    var mod = ng.module("moduloUbicacion");
 
   mod.controller('ubicacionNewCtrl', ['$scope', '$http', 'ubicacionContext', '$state', '$rootScope',
       
        function($scope, $http, ubicacionContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
            
            $scope.createUbicacion = function () {
                alert("Entro a consola2");
                $http.post(ubicacionContext, { 
                    ciudad: $scope.ubicacionCiudad,
                    latitud:$scope.ubicacionLatitud,
                    longitud:$scope.ubicacionLongitud,
                    pais:$scope.ubicacionPais}
                    ).then(function (response) {
                    $state.go('ubicacionList',{ubicacionId: response.data.id} , {reload: true});
                });
            };
        }
        ]);
    })(window.angular);

