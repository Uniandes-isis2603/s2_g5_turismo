(function(ng){
    var mod = ng.module("pagoModule");
    mod.controller("pagoUpdateCtrl",["$scope","$rootScope","$http","pagoContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, pagoContext, $state, $filter) {
            $rootScope.edit = true;
            $scope.data = {};
            id = $state.params.pagoId;
            
           if ($state.params.pagoId !== null && $state.params.pagoId !== undefined) 
           {  
            $http.get(pagoContext+"/"+id).then(function(response){
                var pago = response.data;
                $scope.data.nombrePlan = pago.nombrePlan;
                $scope.data.costo =pago.costo;
                
            });
        }
    
    
            $scope.createPago = function () {
                
                $http.put(pagoContext+"/"+id, { 
                    nombrePlan: $scope.data.nombrePlan,
                    costo:$scope.data.costo}
                    ).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

