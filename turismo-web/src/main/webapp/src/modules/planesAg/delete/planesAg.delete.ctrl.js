(function (ng) {
    var mod = ng.module("miPlanModule");
    mod.constant("miPlanContext", "api/miPlan");
    mod.controller('miPlanDeleteCtrl', ['$scope', '$http', 'miPlanContext', '$state',
        function ($scope, $http, miPlanContext, $state) {
            var miPlanId = $state.params.miPlanId;

            $scope.data = {};
           
            $scope.deletePlanAg = function () {
                
                $http.delete(miPlanContext+"/"+miPlanId, { 
                    
                   }
                    ).then(function (response) {
                    $state.go('miPlanList', {miPlanId: response.data.id}, {reload: true});
                });
            };
        }
        
    ]);
}
)(window.angular);