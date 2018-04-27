(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.controller("tarjetasUpdateCtrl",["$scope","$rootScope","$http","tarjetaContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, tarjetaContext, $state, $filter) {
            $rootScope.edit = true;
            alert("entro aqui y la tarjeta ID");
            console.log($state);
            id = $state.params.tarjetaId;
            alert("entro aqui y la tarjeta ID" +id);
           if ($state.params.tarjetaId !== null && $state.params.tarjetaId !== undefined) 
           {  
            $http.get(tarjetaContext+"/"+id).then(function(response){
                var tarjeta = response.data;
                $scope.tarjetaName = tarjeta.name;
                $scope.tarjetaNumero =tarjeta.numero;
                $scope.tarjetaCVD = tarjeta.CVD;
                $scope.tarjetaCedula = tarjeta.cedula;
                
            });
        }
    
    
            $scope.createTarjeta = function () {
                
                $http.put(tarjetaContext+"/"+id, { 
                    name: $scope.tarjetaName,
                    numero:$scope.tarjetaNumero,
                    CVD:$scope.tarjetaCVD,
                    cedula:$scope.tarjetaCedula}
                    ).then(function (response) {
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

