(function(ng){
    var mod = ng.module("miPlanModule");
    mod.controller("miPlanUpdateCtrl",["$scope","$rootScope","$http","miPlanContext","$state","$filter",

    function ($scope, $rootScope, $http, miPlanContext, $state, $filter) {
            $rootScope.edit = true;
            console.log($state);
            id = $state.params.miPlanId;
            console.log(id);
           if ($state.params.miPlanId !== null && $state.params.miPlanId !== undefined) 
           {  
            $http.get(miPlanContext+"/"+id).then(function(response){
                var miPlan = response.data;
                $scope.idGuia = miPlan.guia.idGuia;
                $scope.idPlan =miPlan.plan.idPlan;
                $scope.fecha = miPlan.fecha;
                
            });
        }
    
    
            $scope.createPlanAg = function () {
                var idG= parseInt($scope.idGuia);
                var idP= parseInt($scope.idPlan);
                var guia = {idGuia:idG};
                var plan = {idPlan:idP };
                $http.put(miPlanContext+"/"+id, { 
                    fecha: $scope.fecha,
                    guia:guia,
                    plan:plan}
                    ).then(function (response) {
                    $state.go('miPlanList', {planId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

