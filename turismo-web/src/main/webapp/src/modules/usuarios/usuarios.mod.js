/**
 * @ngdoc overview
 * @name usuario.module:usuarioModule
 * @description
 * Definición del módulo de Angular de Usuarios. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los Usuarios 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los usuarios en la 
 * URL: 'localhost:8080/usuarios/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar usuarios), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO          | URL                        | VISTAS                 |
 * |-----------------|----------------------------|------------------------|
 * | usuario         | /usuario                   | mainView:              |
 * |                 |                            | usuarios.html          |
 * |                 |                            | usuarios.side.html     |
 * |                 |                            |                        |
 * | usuariosList    | /list                      | listView:              |
 * |                 |                            | usuarios.list.html     |
 * |                 |                            | usuarios.side.html     |
 * |                 |                            |                        |
 * | uuarioDetail    | /{usuarioId:int}/detail    | listView:              |
 * |                 |                            | usuarios.list.html     |
 * |                 |                            | detailView:            |
 * |                 |                            | usuarios.detail.html   |
 * |                 |                            | usuarios.side.html     |
 * |-----------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("usuarioModule", ['ui.router']);
    mod.constant("usuariosContext", "api/usuario");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/usuario/';
            $urlRouterProvider.otherwise("/usuariosList");

            $stateProvider.state('usuarios', {
                url: '/usuario',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'usuarios.html',
                        controller: 'usuariosCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                    
                }
                
            }).state('usuariosList', {
                url: '/list',
                parent: 'usuarios',
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuarios.list.html'
                    }
                }
            }).state('usuariosDetail', {
                url: '/{usuariosId:int}/detail',
                parent: 'usuarios',
                param: {
                    usuariosId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'usuarios.list.html',
                        controller: 'usuariosDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'detailView': {
                        templateUrl: basePath + 'usuarios.detail.html',
                        controller: 'usuariosDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }

            });
        }]);
})(window.angular);


