(function(ng){
    
    var mod = ng.module("paqueteModule");
    mod.constant("paqueContext","api/paquetes");
   mod.controller('paqueteNewCtrl', ['$scope', '$http', 'paqueteContext', '$state', '$rootScope',
       
        function($scope, $http, paqueteContext, $state, $rootScope){
        
            $rootScope.edit = false;

            $scope.data = {};
           
            $scope.createPaquete = function () {
                
                $http.post(paqueteContext, { 
                    
                    completado:false}
                    ).then(function (response) {
                    $state.go('paquetesList', {paqueteId: response.data.id}, {reload: true});
                });
            };
        }
        ]);
    })(window.angular);
