(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.controller("tarjetaCtrl",["$scope","$state","$stateParams","$http","tarjetaContext",function($scope,$state,$stateParams,$http,context)
        {
            //lista vacia
            $scope.records={};
            //carga las tarjetas de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
                 
            });
            
//              el controlador recibió un tarjetaId ??
//             revisa los parámetros (ver el :tarjetaId en la definición de la ruta)
            if (true) {

                // toma el id del parámetro
                id = $state.params.tarjetaId;
                
                
                // obtiene el dato del recurso REST
                $http.get(context +"/"+id)
                        .then(function (response) {
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


