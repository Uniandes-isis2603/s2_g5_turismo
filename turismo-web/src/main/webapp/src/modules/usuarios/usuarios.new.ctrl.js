(function(ng){
    
    var mod = ng.module("usuariosModule");
    mod.constant('usuarioCreate',"api/usuario");
   mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuarioCreate', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name usuarios.controller:usuarioNewCtrl
         * @description
         * Definición del controlador auxiliar para crear usuarios. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} usuariosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Usuarios en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function($scope, $http, usuarioContext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            
            /**
             * @ngdoc function
             * @name createUsuario
             * @methodOf usuario.controller:usuarioNewCtrl
             * @description
             */
            $scope.createUsuario = function () {
                $http.post(usuarioContext, { 
                   id:$scope.usuarioId,
                   nombre:$scope.usuarioNombre,
                   apellido:$scope.usuarioApellido,
                   contrasenia:$scope.usuarioContrasenia,
                   correo:$scope.usuarioCorreo,
                   telefono:$scope.usuarioTelefono,
                   idioma:$scope.usuarioIdioma,
                   esAdministrador:$scope.usuarioEsAdministrador,
                   listaTarjetas:$scope.usuarioListaTarjetas,
                   listaPreferencias:$scope.usuarioListaPreferencias
               }).then(function (response) {
                    $state.go('usuariosList', {usuarioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
    })(window.angular);