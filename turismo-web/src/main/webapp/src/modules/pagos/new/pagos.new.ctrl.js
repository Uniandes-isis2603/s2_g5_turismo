(function(ng){
    
    var mod = ng.module("pagoModule");
    mod.constant("pagoContext","api/pagos");
   mod.controller('pagoNewCtrl', ['$scope', '$http', 'pagoContext', '$state', '$rootScope',
       
        function($scope, $http, pagoContext, $state, $rootScope){
        
            $rootScope.edit = false;

            $scope.data = {};
           
            $scope.createPago = function () {
                
                $http.post(pagoContext, { 
                    nombrePlan: $scope.pagoName,
                    costo:$scope.pagoCosto}
                    ).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
        ]);
    })(window.angular);
