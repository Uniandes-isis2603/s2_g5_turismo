(function(ng){
    
    var mod = ng.module("usuariosModule");
    mod.constant('usuarioCreate',"api/usuario/10000/10002");
   mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuarioCreate', '$state', '$rootScope',
       
        function($scope, $http, usuarioContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
           
            $scope.createFactura = function () {
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
                    $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
    })(window.angular);