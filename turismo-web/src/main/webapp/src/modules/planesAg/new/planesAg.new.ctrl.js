(function(ng){
    
    var mod = ng.module("miPlanModule");
    mod.constant("miPlanContext","api/miPlan");
   mod.controller('miPlanNewCtrl', ['$scope', '$http', 'miPlanContext', '$state', '$rootScope',
       
        function($scope, $http, miPlanContext, $state, $rootScope){
        
             $rootScope.edit = false;
            
            $scope.data = {};
           
           var guia = {idGuia:$scope.idGuia};
           
            $scope.createPlanAg = function () {
                
                $http.post(miPlanContext, { 
                    fecha: $scope.fecha,
                    idGuia:$scope.idGuia
                    }
                    ).then(function (response) {
                    $state.go('miPlanList', {planId: response.data.id}, {reload: true});
                });
            };
            
        }
        ]);
    })(window.angular);
