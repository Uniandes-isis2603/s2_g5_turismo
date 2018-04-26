(function(ng){
    var mod = ng.module("moduloUbicacion");
    mod.controller("ubicacionDetailCtrl",["$scope","$state","$stateParams","$http","ubicacionContext",
        
        
        
        function($scope,$state,$stateParams,$http,context)
        {
            
              //el controlador recibió un ubicacionId ??
             //revisa los parámetros (ver el :ubicacionId en la definición de la ruta)
             
            if ($state.params.ubicacionId !== null && $state.params.ubicacionId !== undefined) {

                // toma el id del parámetro
                id = $state.params.ubicacionId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                             
                            $scope.currentRecord = response.data;
                          
                        });

                // el controlador no recibió un ubicacionId
            }
        }
    ]);
    }
 )(window.angular);


