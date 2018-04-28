(function(ng){
    
    var mod = ng.module("miPlanModule");
    mod.constant("miPlanContext","api/miPlan");
   mod.controller('miPlanNewCtrl', ['$scope', '$http', 'miPlanContext', '$state', '$rootScope',
       
        function($scope, $http, miPlanContext, $state, $rootScope){
        
             $rootScope.edit = false;
            
            $scope.data = {};
           
           
           
           
            $scope.createPlanAg = function () {
                var idG= parseInt($scope.idGuia);
                var idP= parseInt($scope.idPlan);
                var guia = {idGuia:idG};
                var plan = {idPlan:idP };
           
                $http.post(miPlanContext, { 
                    fecha: $scope.fecha,
                    guia:guia,
                    plan:plan
                    }
                    ).then(function (response) {
                    $state.go('miPlanList', {planId: response.data.id}, {reload: true});
                });
            };
            
        }
        ]);
    })(window.angular);
