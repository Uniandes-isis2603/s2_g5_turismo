(function(ng){
    var mod = ng.module("usuarioModule");
    mod.controller("usuariosUpdateCtrl",["$scope","$rootScope","$http","usuariosContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, usuarioContext, $state, $filter) {
            $rootScope.edit = true;
            
           
            id = $state.params.usuarioId;
            
           if ($state.params.usuarioId !== null && $state.params.usuarioId !== undefined) 
           {  
            $http.get(usuarioContext+"/"+id).then(function(response){
                var usuario = response.data;
                $scope.usuarioId = usuario.id;
                $scope.usuarioNombre =usuario.nombre;
                $scope.usuarioApellido =usuario.apellido;
                $scope.usuarioContrasenia =usuario.contrasenia;
                $scope.usuarioCorreo =usuario.correo;
                $scope.usuarioTelefono =usuario.telefono;
                $scope.usuarioIdioma =usuario.idioma;
                $scope.usuarioEsAdministrador =usuario.esAdministrador;
                
            });
        }
    
    
           
            $scope.createFactura = function () {
                alert("Entro a consola2");
                $http.post(usuarioContext, { 
                    id:$scope.usuarioId,
                   nombre:$scope.usuarioNombre,
                   apellido:$scope.usuarioApellido,
                   contrasenia:$scope.usuarioContrasenia,
                   correo:$scope.usuarioCorreo,
                   telefono:$scope.usuarioTelefono,
                   idioma:$scope.usuarioIdioma,
                   esAdministrador:$scope.usuarioEsAdministrador
               }).then(function (response) {
                    $state.go('usuarioList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }    
    ]);
})(window.angular);
