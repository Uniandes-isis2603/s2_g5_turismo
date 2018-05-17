(function (ng) {

    var mod = ng.module("usuariosModule");
    mod.constant('usuarioCreate', "api/usuario");
    mod.constant("preferenciasContext", "api/preferences");
    mod.controller('usuarioNewCtrl', ['$scope', '$http', 'usuarioCreate', '$state', 'preferenciasContext', '$rootScope',
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
        function ($scope, $http, usuarioContext, $state, preferenciasContext, $rootScope) {

            $rootScope.edit = false;

            $scope.data = {};

            $http.get(preferenciasContext).then(function (response) {
                $scope.AllPreferencias = response.data;
            });

            /**
             * @ngdoc function
             * @name createUsuario
             * @methodOf usuario.controller:usuarioNewCtrl
             * @description
             */
            $scope.createUsuario = function () {
                $scope.addCategorias($scope.data.categorias);
                $scope.data.listaPreferencias = $scope.listaPreferencias;
                $http.post(usuarioContext, {
                    id: $scope.usuarioId,
                    nombre: $scope.usuarioNombre,
                    apellido: $scope.usuarioApellido,
                    contrasenia: $scope.usuarioContrasenia,
                    correo: $scope.usuarioCorreo,
                    telefono: $scope.usuarioTelefono,
                    idioma: $scope.usuarioIdioma,
                    esAdministrador: $scope.usuarioEsAdministrador,
                    listaTarjetas: $scope.usuarioListaTarjetas,
                    listaPreferencias: $scope.listaPreferencias
                }).then(function (response)
                {
                    $http.post(usuarioContext + 's/' + response.data.id + '/tarjetas',
                            {
                                name: $scope.tarjetaName,
                                numero: $scope.tarjetaNumero,
                                CVD: $scope.tarjetaCVD,
                                cedula: $scope.tarjetaCedula
                            }).then(function (response)
                    {
                        $state.go('plansList', {usuarioId: response.data.id}, {reload: true});
                    });
                });
            };
            /**
             * @ngdoc function
             * @name addCategorias
             * @methodOf authors.controller:authorUpdateCtrl
             * @description
             * Busca las nuevas categorias en el $scope.
             * @param {Object} Un string con el nombre de las preferencias a asociar
             */
            $scope.addCategorias = function (splitear)
            {
                $scope.listaPreferencias = [];
                var splited = splitear.split("-"), i;
                for (i = 0; i < splited.length; i++)
                {
                    var categoria = {"tipoPlan": splited[i]};
                    $scope.listaPreferencias.push(categoria);
                }
            };
        }
    ]);
})(window.angular);