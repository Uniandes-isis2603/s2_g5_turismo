(function(ng){
    var mod = ng.module("usuariosModule");
    mod.controller("usuariosUpdateCtrl",["$scope","$rootScope","$http","usuariosContext","$state","$filter",
    
    
    
    
    /**
         * @ngdoc controller
         * @name usuarios.controller:usuariosUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar usuarios. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} usuariosContext Constante injectada que contiene la ruta
         * donde se encuentra el API de usuarios en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
    function ($scope, $rootScope, $http, usuariosContext, $state, $filter) {
            $rootScope.edit = true;
            
           
            id = $state.params.usuarioId;
            
           if ($state.params.usuarioId !== null && $state.params.usuarioId !== undefined) 
           {  
            /**
             * @ngdoc function
             * @name getUsuarioID
             * @methodOf usuarios.controller:usuarioUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la usuario por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del usuario o API donde se puede consultar.
             */   
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
    
    
            /**
             * @ngdoc function
             * @name createUsuario
             * @methodOf usuarios.controller:usuarioUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar el usuario 
             * por ID en formato JSON.
             * @param {String} id El ID del usuario a actualizar.
             * @param {Object} usuario Objeto con la información nueva del usuario.
             */
            $scope.createUsuario = function () {
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
