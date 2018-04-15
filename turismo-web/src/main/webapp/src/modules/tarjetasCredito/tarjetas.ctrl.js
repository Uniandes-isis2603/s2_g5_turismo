(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.cotroller("tarjetaCtrl",["$scope","$state","$stateParams","$http","tarjetaContext",function($scope,$state,$stateParams,$http,context)
        {
            //lista vacia
            $scope.records={};
            //carga las tarjetas de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
            });
            
             // el controlador recibió un cityId ??
            // revisa los parámetros (ver el :cityId en la definición de la ruta)
            if ($stateParams.tarjetaId !== null && $stateParams.tarjetaId !== undefined) {

                // toma el id del parámetro
                id = $stateParams.tarjetId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                        .then(function (response) {
                            // $http.get es una promesa
                            // cuando llegue el dato, actualice currentRecord
                            $scope.currentRecord = response.data;
                        });

                // el controlador no recibió un cityId
            }
        }]);
    
})(window.angular);


