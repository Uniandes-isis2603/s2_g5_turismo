(function(ng){
    var mod = ng.module("miPlanModule");
    mod.controller("miPlanUpdateCtrl",["$scope","$rootScope","$http","miPlanContext","$state","$filter",

    function ($scope, $rootScope, $http, miPlanContext, $state, $filter) {
            $rootScope.edit = true;
            $scope.data = {};
            id = $state.params.miPlanId;
           if ($state.params.miPlanId !== null && $state.params.miPlanId !== undefined) 
           {  
            $http.get(miPlanContext+"/"+id).then(function(response){
                var miPlan = response.data;
                $scope.data.guia = miPlan.guia;
                console.log( $scope.data.guia );
                $scope.data.plan =miPlan.plan;
                console.log($scope.data.plan);
                $scope.data.fecha = miPlan.fecha;
                
            });
        }
    
    
            $scope.createPlanAg = function () {
                var idG= parseInt($scope.data.idGuia);
                var idP= parseInt($scope.data.idPlan);
                var guia = {idGuia:idG};
                var plan = {idPlan:idP };
                $http.put(miPlanContext+"/"+id, { 
                    fecha: $scope.data.fecha,
                    guia:guia,
                    plan:plan}
                    ).then(function (response) {
                    $state.go('miPlanList', {planId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

