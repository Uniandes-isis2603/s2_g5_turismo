(function(ng){
    var mod = ng.module("paqueteModule");
    mod.controller("paqueteUpdateCtrl",["$scope","$rootScope","$http","paqueteContext","$state","$filter",
    
    
    
    function ($scope, $rootScope, $http, paqueteContext, $state, $filter) {
           
            var listaPagos ;
            var listaPlanes;
            $rootScope.edit = true;
            console.log($state);
            id = $state.params.paqueteId;
            
           if ($state.params.paqueteId !== null && $state.params.paqueteId !== undefined) 
           {  
            $http.get(paqueteContext+"/"+id).then(function(response){
                var paquete = response.data;
                
                $scope.completado = paquete.completado;
                $scope.idPagos =paquete.pagos.id;
                $scope.idPlanes =paquete.planes.id;
                listaPagos=paquete.pagos;
                listaPlanes=paquete.planes;
                console.log(listaPagos);
            });
        }
    
    
            $scope.updatePaquete = function () {
                var idP=parseInt($scope.idPagos);
                var idPl=parseInt($scope.idPlanes);
                var pagoAdd={id:idP};
                var planAdd={id:idPl};
                listaPagos.push(pagoAdd);
                listaPlanes.push(planAdd);
                console.log($scope.idPagos);
                console.log(listaPagos);
                $http.put(paqueteContext+"/"+id, { 
                    completado: $scope.completado,
                    pagos:listaPagos,
                    planes:listaPlanes
                }
                    ).then(function (response) {
                    $state.go('paquetesList', {paqueteId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

