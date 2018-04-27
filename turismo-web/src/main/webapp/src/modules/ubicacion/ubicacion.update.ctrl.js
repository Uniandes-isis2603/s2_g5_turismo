(function(ng){
    var mod = ng.module("moduloUbicacion");
    mod.controller("ubicacionUpdateCtrl",["$scope","$rootScope","$http","ubicacionContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, ubicacionContext, $state, $filter) {
            $rootScope.edit = true;
            alert("entro aqui y la ubicacion ID");
            console.log($state);
            id = $state.params.ubicacionId;
            alert("entro aqui y la ubicacion ID" +id);
           if ($state.params.ubicacionId !== null && $state.params.ubicacionId!== undefined) 
           {  
            $http.get(ubicacionContext+"/"+id).then(function(response){
                var ubicacion = response.data;
                $scope.ubicacionCiudad = ubicacion.ciudad;
                $scope.ubicacionLongitud =ubicacion.longitud;
                $scope.ubicacionLatitud = ubicacion.latitud;
                $scope.ubicacionPais = ubicacion.pais;
                
            });
        }
    
    
           $scope.createUbicacion = function () {
                alert("Entro a consola2");
                $http.post(ubicacionContext, { 
                    ciudad: $scope.ubicacionCiudad,
                    latitud:$scope.ubicacionLatitud,
                    longitud:$scope.ubicacionLongitud,
                    pais:$scope.ubicacionPais}
                    ).then(function (response) {
                    $state.go('ubicacionList',{ubicacionId: response.data.id} , {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);



