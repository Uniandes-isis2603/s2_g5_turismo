(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.controller('planUpdateCtrl', ['$scope', '$http', 'plansContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name plans.controller:planUpdateCtrl
         * @description
         * Definición del controlador auxiliar para actualizar Plans. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} planContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Plans en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, plansContext, $state, $rootScope) {
            $rootScope.edit = true;

            $scope.data = {"ubicacion":{}};

            var idPlan = $state.params.planId;

            /**
             * @ngdoc function
             * @name getPlanID
             * @methodOf plans.controller:planUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra la plan por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de la plan o API donde se puede consultar.
             */
            $http.get(plansContext + '/' + idPlan).then(function (response) {
                var plan = response.data;
                $scope.data.archivo = plan.archivo;
                $scope.data.cantidadPersonas = plan.cantidadPersonas;
                $scope.data.description = plan.descripcion;
                $scope.data.duracion = plan.duracion;
                $scope.data.name = plan.name;
                $scope.data.precio = plan.precio;
                $scope.data.restricciones = plan.restricciones;
                $scope.data.ubicacion.latitud = plan.ubicacion.latitud;
                $scope.data.ubicacion.longitud = plan.ubicacion.longitud;
                $scope.data.ubicacion.pais = plan.ubicacion.pais;
                $scope.data.ubicacion.ciudad = plan.ubicacion.ciudad;
            });

            /**
             * @ngdoc function
             * @name createPlan
             * @methodOf plans.controller:planUpdateCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para actualizar la plan 
             * por ID en formato JSON.
             * @param {String} id El ID de la plan a actualizar.
             * @param {Object} plan Objeto con la información nueva de la plan.
             */
            $scope.createPlan = function () {
                $http.put(plansContext + "/" + idPlan, $scope.data).then(function (response) {
                    $state.go('plansList', {planId: response.data.idPlan}, {reload: true});
                });
            }
        }]);
}
)(window.angular);