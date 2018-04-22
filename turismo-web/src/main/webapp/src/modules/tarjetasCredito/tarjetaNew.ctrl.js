(function(ng){
    
    var mod = ng.module("moduloTarjetas");
    mod.constant("contextoTarjeta","api/usuarios/10001/tarjetas");
   mod.controller('tarjetaNewCtrl', ['$scope', '$http', 'contextoTarjeta', '$state', '$rootScope',
       
        function($scope, $http, contextoTarjeta, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
            alert("Entro a consola1");
            $scope.createTarjeta = function () {
                alert("Entro a consola2");
                $http.post(contextoTarjeta, { 
                    name: $scope.tarjetaName,
                    numero:$scope.tarjetaNumero,
                    CVD:$scope.tarjetaCVD,
                    cedula:$scope.tarjetaCedula}
                    ).then(function (response) {
                    $state.go('tarjetasList', {tarjetaId: response.data.id}, {reload: true});
                });
            };
        }
        ]);
    })(window.angular);