(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.controller('planDetailCtrl', ['$scope', '$http', 'plansContext', '$state',
        /**
         * @ngdoc controller
         * @name plans.controller:planDetailCtrl
         * @description
         * Definición de un controlador auxiliar del módulo plans. 
         * Se crea el controlador con el cual se manejan las vistas de detalle
         * del módulo.
         * @param {Object} $scope Referencia injectada al Scope definida para este
         * controlador, el scope es el objeto que contiene las variables o 
         * funciones que se definen en este controlador y que son utilizadas 
         * desde el HTML.
         * @param {Object} $http Objeto injectado para la manejar consultas HTTP
         * @param {Object} plansContext Constante injectada que contiene la ruta
         * donde se encuentra el API de plans en el Backend.
         * @param {Object} $state Dependencia injectada en la que se recibe el 
         * estado actual de la navegación definida en el módulo.
         */
        function ($scope, $http, plansContext, $state) {           
            if (($state.params.planId !== undefined)&& ($state.params.planId !== null)) {
             /**
             * @ngdoc function
             * @name getPlanID
             * @methodOf plans.controller:planDetailCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentra el plan por ID en formato JSON.
             * @param {String} URL Dirección donde se encuentra el recurso
             * del plan o API donde se puede consultar.
             */
                $http.get(plansContext + '/' + $state.params.planId).then(function (response) {
                    $scope.currentPlan = response.data;
                });
            }
        }
    ]);
}
)(window.angular);