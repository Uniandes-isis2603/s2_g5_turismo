(function(ng){
    var mod = ng.module("moduloFacturas");
    mod.controller("facturaCtrl",["$scope","$state","$stateParams","$http","facturaContext",
        
        
        
        function($scope,$state,$stateParams,$http,context)
        {
            //lista vacia
            $scope.records={};
            alert("entro");
            //carga las facturas de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
            });
            
}
    ]);
    }
 )(window.angular);


