(function (ng) {
    var mod = ng.module("usuariosModule", ['ui.router']);
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


