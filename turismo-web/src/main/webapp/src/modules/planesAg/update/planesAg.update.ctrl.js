(function(ng){
    var mod = ng.module("miPlanModule");
    mod.controller("miPlanUpdateCtrl",["$scope","$rootScope","$http","miPlanContext","$state","$filter",

    function ($scope, $rootScope, $http, pagoContext, $state, $filter) {
            $rootScope.edit = true;
            console.log($state);
            id = $state.params.miPlanId;
           if ($state.params.miPlanId !== null && $state.params.miPlanId !== undefined) 
           {  
            $http.get(miPlanContext+"/"+id).then(function(response){
                var miPlan = response.data;
                $scope.guia.id = pago.nombrePlan;
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

