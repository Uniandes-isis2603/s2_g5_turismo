(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.controller("facturasUpdateCtrl",["$scope","$rootScope","$http","facturaContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, facturaContext, $state, $filter) {
            $rootScope.edit = true;
            
           
            id = $state.params.facturaId;
            
           if ($state.params.facturaId !== null && $state.params.facturaId !== undefined) 
           {  
            $http.get(facturaContext+"/"+id).then(function(response){
                var factura = response.data;
                $scope.facturaId = factura.id;
                $scope.facturaCosto =factura.costo;
                
            });
        }
    
    
           
            $scope.createFactura = function () {
                alert("Entro a consola2");
                $http.put(facturaContext+"/"+id, { 
                    id:$scope.facturaId,
                   costo:$scope.facturaCosto
               }).then(function (response) {
                    $state.go('facturasList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);


