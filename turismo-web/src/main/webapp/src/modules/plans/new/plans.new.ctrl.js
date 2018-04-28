(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.controller('planNewCtrl', ['$scope', '$http', 'plansContext', '$state', '$rootScope',
        /**
         * @ngdoc controller
         * @name plans.controller:planNewCtrl
         * @description
         * Definición del controlador auxiliar para crear Planes. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} plansContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Planes en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         * @param {Object} booksContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Libros en el Backend.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, plansContext, $state, $rootScope) {
            $rootScope.edit = false;

            $scope.data = {};
            
            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf plans.controller:planNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el plan.
             * @param {Object} plan Objeto con el nuevo plan.
             */
            $scope.createPlan = function () {
                $http.post(plansContext, $scope.data).then(function (response) {
                    $state.go('plansList', {planId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);