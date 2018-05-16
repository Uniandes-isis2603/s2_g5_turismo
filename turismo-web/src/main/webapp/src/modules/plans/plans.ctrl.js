(function (ng) {
    var mod = ng.module("planModule");
    mod.constant("plansContext", "api/plans");
    mod.controller('planCtrl', ['$rootScope', '$scope', '$http', 'plansContext',
        /**
         * @ngdoc controller
         * @name plans.controller:planCtrl
         * @description
         * Definición del controlador de Angular del módulo plans. 
         * Se crea el controlador con el cual se maneja el módulo.
         * En el controlador se definen los atributos y métodos que pueden
         * ser accedidos desde el HTML utilizando el $scope.
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
        function ($rootScope, $scope, $http, plansContext) {
            /**
             * @ngdoc function
             * @name getPlans
             * @methodOf plans.controller:planCtrl
             * @description
             * Esta función utiliza el protocolo HTTP para obtener el recurso 
             * donde se encuentran los planes en formato JSON. El recurso
             * puede ser un archivo o un API Rest. La función se ejecuta
             * automáticamente cuando el controlador es accedido desde el
             * navegador.
             * @param {String} URL Dirección donde se encuentra el recurso
             * de los planes o API donde se puede consultar.
             */
            $http.get(plansContext).then(function (response) {
                $scope.plansRecords = response.data;



            });
            /**
             * Retorna el item si cumple con las condiciones de rango
             * @param {type} prop propiedad del item a analizar
             * @param {type} aux rango inferior
             * @param {type} aux2 rango superor
             * @returns {Function}
             */
            $scope.betweenValuesPrecio = function (prop, aux, aux2)
            {
                return function (item)
                {
                    return item[prop] >= aux && item[prop] <= aux2;
                };
            };
            $scope.betweenValuesValoracion = function (prop, aux, aux2)
            {
                return function (item)
                {
                    var vals = item[prop];
                    var promedio = 0;
                    for(var i = 0; i < vals.length; i++)
                    {
                        promedio = promedio + vals[i].calificacion;
                    }
                    promedio = promedio/vals.length;
                    return promedio >= aux && promedio <=aux2;
                };
            };
            $rootScope.minRangeSlider = {
                minValue: 10000,
                maxValue: 1000000,
                options: {
                    floor: 0,
                    ceil: 1000000,
                    step: 10000,
                    translate: function (value){
                        return '$' + value;
                    }
                    
                }
            };
            $rootScope.minRangeSliderP = {
                minValue: 1,
                maxValue: 10,
                options: {
                    floor: 1,
                    ceil: 10,
                    step: 1
                }
            };
            $rootScope.minRangeSliderV = {
                minValue: 1,
                maxValue: 5,
                options: {
                    floor: 1,
                    ceil: 5,
                    step: 1,
                    showTicksValues: true
                }
            };
            
        }
    ]);
}
)(window.angular);