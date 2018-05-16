(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.constant("preferenciasContext", "api/preferences");
    mod.constant("guidesContext", "api/guides");
    mod.controller('planNewCtrl', ['$scope', '$http', 'plansContext', '$state', 'preferenciasContext', '$rootScope', 'guidesContext',
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
         * @param {Object} preferenciasContext Constante injectada que contiene la ruta
         * donde se encuentra el API de preferencias en el Backend.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, plansContext, $state, preferenciasContext, $rootScope, guidesContext) {
            $rootScope.edit = false;

            $scope.data = {};

            $http.get(preferenciasContext).then(function (response) {
                $scope.AllPreferencias = response.data;
            });
            $http.get(guidesContext).then(function (response) {
                $scope.AllGuides = response.data;
            });
            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf plans.controller:planNewCtrl
             * @description
             */
            $scope.createPlan = function () {
                $scope.addCategorias($scope.data.categorias);
                $scope.addGuides($scope.data.guides);
                $scope.data.categoriasPlan = $scope.categoriasPlan;
                $scope.data.guiasPlan = $scope.guidesPlan;
                $http.post(plansContext, $scope.data).then(function (response) {
                    $state.go('plansList', {planId: response.data.id}, {reload: true});
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
                $scope.categoriasPlan = [];
                var splited = splitear.split("-"), i;
                for (i = 0; i < splited.length; i++)
                {
                    var categoria = {"tipoPlan": splited[i]};
                    $scope.categoriasPlan.push(categoria);
                }
            };

            /**
             * @ngdoc function
             * @name addGuides
             * @methodOf authors.controller:authorUpdateCtrl
             * @description
             * Busca las nuevas categorias en el $scope.
             * @param {Object} Un string con los id de guias a asociar a el plan
             */
            $scope.addGuides = function (splitear)
            {
                $scope.guidesPlan = [];
                var splited = splitear.split("-"), i;
                for (i = 0; i < splited.length; i++)
                {
                    var guide = {"idGuia": splited[i]};
                    $scope.guidesPlan.push(guide);
                }
            };
        }
    ]);
}
)(window.angular);