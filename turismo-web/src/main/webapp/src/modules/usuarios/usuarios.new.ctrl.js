(function(ng){
    
    var mod = ng.module("usuarioModule");
    mod.constant('usuarioContext2',"api/usuario/10000/10002");
   mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuarioContext2', '$state', '$rootScope',
       
        function($scope, $http, usuarioContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
           
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