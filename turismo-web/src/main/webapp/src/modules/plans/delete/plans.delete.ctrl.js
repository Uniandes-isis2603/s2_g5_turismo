(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.controller('planDeleteCtrl', ['$scope', '$http', 'plansContext', '$state',
        /**
         * @ngdoc controller
         * @name plans.controller:planDeleteCtrl
         * @description
         * Definición del controlador auxiliar para eliminar Planes. 
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} planContext Constante injectada que contiene la ruta
         * donde se encuentra el API de Planes en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, plansContext, $state) {
            var idPlan = $state.params.planId;
            /**
             * @ngdoc function
             * @name deletePlan
             * @methodOf plans.controller:planDeleteCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para eliminar la plan.
             * @param {String} id El ID de la plan a eliminar.
             */
            $scope.deletePlan = function () {
                $http.delete(plansContext + '/' + idPlan, {}).then(function (response) {
                    $state.go('plansList', {plansId: response.data.idPlan}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);