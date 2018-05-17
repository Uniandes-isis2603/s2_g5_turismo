/**
 * @ngdoc overview
 * @name login.module:loginModule
 * @description
 * Definición del módulo de Angular de Manejo de sesión.
 * Los estados definidos en este modulo son:
 * ```
 * | ESTADO           | URL                        | VISTAS                 |
 * |------------------|----------------------------|------------------------|
 * | login            | /login                     | mainView:              |
 * |                  |                            | login.html             |
 * | logout           | /logout                    | listView:              |
 * |                  |                            | logout.html            |
 * |------------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("loginModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

            var basePath = 'src/modules/login/';

            $urlRouterProvider.otherwise("/login");


            $stateProvider.state('login', {
                url: '/login',
                data: {
                    requireLogin:false,
                    roles: []
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'login.html',
                        controller: 'loginCtrl'
                    },
                    'initialView': {
                        templateUrl: basePath + 'inicial.html',
                        controller: 'loginCtrl'
                    } 
                }
            }).state('logout', {
                url: '/logout',
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'logout.html',
                        controller: 'logoutCtrl'
                    },
                    'initialView': {
                        templateUrl: basePath + 'inicial.html',
                        controller: 'loginCtrl'
                    } 
                }
            }).state('createUsuario', {
                url: '/create',
                data: {
                    requireLogin: false,
                    roles: []
                }
                ,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.create.html',
                        controller:  'usuarioNewCtrl'
                    }
                }
            })
            ;
        }
    ]);
})(window.angular);

