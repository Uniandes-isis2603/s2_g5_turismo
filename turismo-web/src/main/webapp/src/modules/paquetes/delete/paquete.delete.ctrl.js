(function (ng) {
    var mod = ng.module("paqueteModule");
    mod.constant("paqueteContext", "api/paquetes");
    mod.controller('paqueteDeleteCtrl', ['$scope', '$http', 'paqueteContext', '$state',
        function ($scope, $http, paqueteContext, $state) {
            var paqueteId = $state.params.paqueteId;

            $scope.data = {};
           
            $scope.deletePaquete = function () {
                
                $http.delete(paqueteContext+"/"+paqueteId, { 
                    
                   }
                    ).then(function (response) {
                    $state.go('paquetesList', {paqueteId: response.data.id}, {reload: true});
                });
            };
        }
        
    ]);
}
)(window.angular);