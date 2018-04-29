(function(ng){
    var mod = ng.module("pagoModule");
    mod.controller("pagoUpdateCtrl",["$scope","$rootScope","$http","pagoContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, pagoContext, $state, $filter) {
            $rootScope.edit = true;
            console.log($state);
            id = $state.params.pagoId;
            console.log(id);
           if ($state.params.pagoId !== null && $state.params.pagoId !== undefined) 
           {  
            $http.get(pagoContext+"/"+id).then(function(response){
                var pago = response.data;
                $scope.nombrePlan = pago.nombrePlan;
                $scope.costo =pago.costo;
                
            });
        }
    
    
            $scope.createPago = function () {
                
                $http.put(pagoContext+"/"+id, { 
                    nombrePlan: $scope.pagoName,
                    costo:$scope.pagoCosto}
                    ).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

