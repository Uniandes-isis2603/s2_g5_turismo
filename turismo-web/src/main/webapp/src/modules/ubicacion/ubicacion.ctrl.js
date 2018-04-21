(function(ng){
    var mod = ng.module("moduloUbicacion");
    mod.controller("ubicacionCtrl",["$scope","$state","$stateParams","$http","ubicacionContext",function($scope,$state,$stateParams,$http,context)
        {
            //lista vacia
            $scope.records={};
            //carga las ubicacions de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
            });
            
             // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
//            if ($stateParams.ubicacionId !== null && $stateParams.ubicacionId !== undefined) {
//
//                // toma el id del parámetro
//                id = $stateParams.tarjetId;
//                // obtiene el dato del recurso REST
//                $http.get(context + "/" + id)
//                        .then(function (response) {
//                            // $http.get es una promesa
//                            // cuando llegue el dato, actualice currentRecord
//                            $scope.currentRecord = response.data;
//                        });
//
//                // el controlador no recibió un cityId
//            }
        }
    ]);
    }
 )(window.angular);


