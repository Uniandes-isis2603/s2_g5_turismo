(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.constant("preferenciasContext", "api/preferences");
    mod.controller('planNewCtrl', ['$scope', '$http', 'plansContext', '$state', 'preferenciasContext', '$rootScope', '$filter',
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
         * donde se encuentra el API de Libros en el Backend.
         * @param {Object} $rootScope Referencia injectada al Scope definida para
         * toda la aplicación.
         */
        function ($scope, $http, plansContext, $state, preferenciasContext, $rootScope, $filter) {
            $rootScope.edit = false;

            $scope.data = {"categoriasPlan":[{tipoPlan: "familiar"}]};
            
            // Este arreglo guardara los ids de los preferencias asociados y por asociar al plan.
            var nombresCat = [];
            
            // Este arreglo mostrará los preferencias una vez esten filtrados visualmente por lo que el plan ya tiene asociado.
            $scope.allPreferenciasShow = [];
            
            
            
            $http.get(preferenciasContext).then(function (response) {
                    $scope.Allpreferencias = response.data;
                    $scope.preferenciasAuthor = $scope.Allpreferencias;
                    $scope.allPreferenciasShow = [];
                    $scope.allPreferenciasAuthor = $scope.Allpreferencias;
                    $scope.data.categoriasPlan = $scope.Allpreferencias;

                });
            //funciones para el drag and drop de HTML5 nativo
            $scope.allowDrop = function (ev) {
                ev.preventDefault();
            };

            $scope.drag = function (ev) {
                ev.dataTransfer.setData("text", ev.target.id);
            };

            $scope.dropAdd = function (ev) {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("text");
                ev.target.appendChild(document.getElementById(data));
                //Cuando un preferencia se añade al plan, se almacena su id en el array nombresCat
                nombresCat.push("" + data);
            };

            $scope.dropDelete = function (ev) {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("text");
                ev.target.appendChild(document.getElementById(data));
                //Para remover el preferencia que no se va asociar, por eso se usa el splice que quita el id del preferencia en el array nombresCat
                var index = nombresCat.indexOf(data);
                if (index > -1) {
                    nombresCat.splice(index, 1);
                }
            };
            
            /**
             * @ngdoc function
             * @name createEditorial
             * @methodOf plans.controller:planNewCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para crear el plan.
             * @param {Object} plan Objeto con el nuevo plan.
             */
            $scope.createPlan = function () {
                $scope.addCategorias();
               $scope.data.categoriasPlan = $scope.allpreferencias;
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
             */
            $scope.addCategorias = function () {
                for (var ite in nombresCat) {
                    for (var all in $scope.Allpreferencias) {
                        if ($scope.Allpreferencias[all].id === parseInt(nombresCat[ite])) {
                            $scope.allPreferenciasAuthor.push($scope.Allpreferencias[all]);
                        }
                    }
                }
            };
        }
    ]);
}
)(window.angular);