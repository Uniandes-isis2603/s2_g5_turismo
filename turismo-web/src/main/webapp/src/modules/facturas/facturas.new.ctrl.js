(function(ng){
    
    var mod = ng.module("moduloFacturas");
    mod.constant('facturaContext2',"api/facturas/10000/10002");
   mod.controller('facturaNewCtrl', ['$scope', '$http', 'facturaContext2', '$state', '$rootScope',
       
        function($scope, $http, facturaContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
           
            $scope.createFactura = function () {
                alert("Entro a consola2");
                $http.post(facturaContext, { 
                    id:$scope.facturaId,
                   costo:$scope.facturaCosto
               }).then(function (response) {
                    $state.go('facturaList', {facturaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
    })(window.angular);