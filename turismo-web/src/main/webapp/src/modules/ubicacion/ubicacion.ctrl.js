(function(ng){
    var mod = ng.module("moduloUbicacion");
    mod.controller("ubicacionCtrl",["$scope","$state","$stateParams","$http","ubicacionContext",
        
        
        
        function($scope,$state,$stateParams,$http,context)
        {
            
            //lista vacia
            $scope.records={};
            //carga las ubicacions de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
                 
            });
        }
    ]);
    }
 )(window.angular);


