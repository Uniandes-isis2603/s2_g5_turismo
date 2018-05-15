(function(ng){
    var mod = ng.module("paqueteModule");
    mod.controller("paqueteUpdateCtrl",["$scope","$rootScope","$http","paqueteContext","$state","$filter",
    
    
    
    function ($scope, $rootScope, $http, paqueteContext, $state, $filter) {
           
            var listaPagos ;
            var listaPlanes;
            
            $rootScope.edit = true;
            id = $state.params.paqueteId;
            
           if ($state.params.paqueteId !== null && $state.params.paqueteId !== undefined) 
           {  
            $http.get(paqueteContext+"/"+id).then(function(response){
                var paquete = response.data;
                
                
                listaPagos=paquete.pagos;
                listaPlanes=paquete.planes;
            });
            
        }
    
    
            $scope.updatePaquete = function () {
                
            var costoR;
            var nombreR;
                
                console.log($scope.fecha);
                var idNP=parseInt($scope.idPlan);
                $http.get("api/plans/"+idNP).then(function(response){
                var planR = response.data;
                
                costoR=parseInt(planR.precio);
                nombreR =planR.name;
                
                var NPlan={idPlan:idNP};
                var pagoAdd={id:999999,costo:costoR,nombrePlan:nombreR};
                var planAdd={id:9999999,fecha:$scope.fecha,plan:NPlan};
                console.log(NPlan);
                console.log(pagoAdd);
                listaPagos.push(pagoAdd);
                listaPlanes.push(planAdd);
                $http.put(paqueteContext+"/"+id, { 
                    completado: $scope.completado,
                    pagos:listaPagos,
                    planes:listaPlanes
                }
                    ).then(function (response) {
                    $state.go('paquetesList', {paqueteId: response.data.id}, {reload: true});
                });
                console.log(costo);
            });
                
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

