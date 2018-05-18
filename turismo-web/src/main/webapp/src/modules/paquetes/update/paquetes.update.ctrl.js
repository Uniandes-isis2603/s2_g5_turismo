(function(ng){
    var mod = ng.module("paqueteModule");
    mod.controller("paqueteUpdateCtrl",["$scope","$rootScope","$http","paqueteContext","$state","$filter",
    
    
    
    function ($scope, $rootScope, $http, paqueteContext, $state, $filter) {
           
            var listaPagos ;
            var listaPlanes;
            var idNP;
            var idGu=null;
            
            
            $rootScope.edit = true;
            id = $state.params.paqueteId;
            
           if ($state.params.paqueteId !== null && $state.params.paqueteId !== undefined) 
           {  
            $http.get(paqueteContext+"/"+id).then(function(response){
                var paquete = response.data;
                $scope.completado=response.data.completado;
                listaPagos=paquete.pagos;
                listaPlanes=paquete.planes;
            }).then(
            $http.get("api/plans").then(function (response) {
                
                $scope.plansRecords = response.data;
                var i;
                var j;
                for ( i = 0; i< listaPlanes.length; i++){
                    for (j=0;j<$scope.plansRecords.length;j++)
                    {
                        
                       if(listaPlanes[i].plan.idPlan==$scope.plansRecords[j].idPlan)
                       {
                          
                           $scope.plansRecords.splice(j, 1);
                       }
                    }
                    
                }
               
            }));
        }
            
            $scope.idPlan= function (param)
            {
                if(idNP!=param)
                {
                    idNP=param.plan.idPlan;
                    $scope.guiasRecords = param.plan.guiasPlan;
                    idGu=null;
                }
                
                
            };
            
            $scope.idGuia= function (param)
            {
                idGu=param.guia.idGuia;
                console.log(param.guia.idGuia);
            };
        
    
            $scope.updatePaquete = function () {
                
            var costoR;
            var nombreR;
                
               
                $http.get("api/plans/"+idNP).then(function(response){
                var planR = response.data;
                
                costoR=parseInt(planR.precio);
                nombreR =planR.name;
                
                var NPlan={idPlan:idNP};
                var NGuia=null;
                if(idGu!==null)
                {
                NGuia={idGuia:idGu};
            }
            if($scope.fecha===undefined)
            {
        }
            else{
                var pagoAdd={id:999999,costo:costoR,nombrePlan:nombreR};
                var planAdd={id:9999999,fecha:$scope.fecha,plan:NPlan,guia:NGuia};
                console.log(NPlan);
                console.log(pagoAdd);
                listaPagos.push(pagoAdd);
                listaPlanes.push(planAdd);
                $http.put(paqueteContext+"/"+id, { 
                    completado:false,
                    pagos:listaPagos,
                    planes:listaPlanes,
                   
                }
                    ).then(function (response) {
                    $state.go('paqueteUpdate', {paqueteId: response.data.id}, {reload: true});
                });
            
                    }});
                
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

