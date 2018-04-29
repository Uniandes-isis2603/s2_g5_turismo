(function(ng){
    var mod = ng.module("usuariosModule");
    mod.controller("usuariosUpdateCtrl",["$scope","$rootScope","$http","usuariosContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, usuariosContext, $state, $filter) {
            $rootScope.edit = true;
            
           
            id = $state.params.usuarioId;
            
           if ($state.params.usuarioId !== null && $state.params.usuarioId !== undefined) 
           {  
            $http.get(usuariosContext+"/"+id).then(function(response){
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
                $http.post(usuariosContext, { 
                    id:$scope.usuarioId,
                   nombre:$scope.usuarioNombre,
                   apellido:$scope.usuarioApellido,
                   contrasenia:$scope.usuarioContrasenia,
                   correo:$scope.usuarioCorreo,
                   telefono:$scope.usuarioTelefono,
                   idioma:$scope.usuarioIdioma,
                   esAdministrador:$scope.usuarioEsAdministrador
               }).then(function (response) {
                    $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }    
    ]);
})(window.angular);
