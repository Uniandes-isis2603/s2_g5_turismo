(function(ng){
    var mod = ng.module("moduloTarjetas");
    mod.controller("tarjetaCtrl",["$scope","$state","$stateParams","$http","tarjetaContext",
        
        function($scope,$state,$stateParams,$http,context)
        {
            //lista vacia
            $scope.records={};
            //carga las tarjetas de un usuario
            $http.get(context).then(function(response){
                $scope.records = response.data;
                 
            });
            

          }

     ]);
 }
 )(window.angular);


