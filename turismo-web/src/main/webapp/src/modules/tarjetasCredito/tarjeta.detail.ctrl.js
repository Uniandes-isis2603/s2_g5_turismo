(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.controller("tarjetaDetailCtrl",["$scope","$state","$stateParams","$http","tarjetaContext",
        
        function($scope,$state,$stateParams,$http,context)
        {
           
                
            if ($state.params.tarjetaId !== null && $state.params.tarjetaId !== undefined) {

                   
                // toma el id del parámetro
                id = $state.params.tarjetaId;
                
                
                
                // obtiene el dato del recurso REST
                $http.get(context +"/"+id).then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                             $scope.currentRecord = response.data;
                          
                        });

                // el controlador no recibió un tarjetaId
            }
        }
    ]);
    }
 )(window.angular);
