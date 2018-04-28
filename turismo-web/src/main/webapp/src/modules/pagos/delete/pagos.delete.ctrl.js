(function (ng) {
    var mod = ng.module("pagoModule");
    mod.constant("pagosContext", "api/pagos");
    mod.controller('pagoDeleteCtrl', ['$scope', '$http', 'pagosContext', '$state',
        function ($scope, $http, pagosContext, $state) {
            var pagoId = $state.params.pagoId;

            $scope.data = {};
           
            $scope.deletePago = function () {
                
                $http.delete(pagosContext+"/"+pagoId, { 
                    
                   }
                    ).then(function (response) {
                    $state.go('pagosList', {pagoId: response.data.id}, {reload: true});
                });
            };
        }
        
    ]);
}
)(window.angular);