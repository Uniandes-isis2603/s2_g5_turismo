/**
 * @ngdoc overview
 * @name comentarios.module:comentariosModule
 * @description
 * Definición del módulo de Angular de comentarios. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los comentarios 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los comentarios en la 
 * URL: 'localhost:8080/comentarios/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar comentarios), el controlador y la vista 
 * correspondiente.
 */
(function (ng) {
    // Definición del módulo
    var mod = ng.module("comentariosModule", ['ui.router']);
    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/comentarios/';
            // Mostrar la lista de comentarios será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/comentariosList");
            // Definición del estado 'comentariosList' donde se listan los comentarios
            $stateProvider.state('comentariosList', {
                // Url que aparecerá en el browser
                url: '/comentarios/list',
                params: {
                    blogId: null,
                    comentarioId: null,
                    Id: null
                    
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'comentarios.list.html',
                        controller: 'comentariosCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state("comentarioCreate",{
                       url:"/create",
                       data: {
                        requireLogin: true,
                        roles: []
                       },
                       parent:"comentariosList",
                       views:{
                           'detailView':{
                               templateUrl:basePath +"comentario.create.html",
                               controller:"comentarioNewctrl"
                           }
                       }
                   }).state('comentarioUpdate', {
                url: '/update/{comentarioId:int}',
                data: {
                        requireLogin: true,
                        roles: []
                       },
                parent: 'comentariosList',
                param: {
                   
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + "comentario.create.html",
                        controller: 'comentarioUpdateCtrl'
                    }
                   
                }
            }).state('ComentarioDelete', {
                url: '/delete/{ComentarioId:int}',
                data: {
                        requireLogin: true,
                        roles: []
                       },
                parent: 'comentariosList',
                param: {
                    
                    
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'comentario.delete.html',
                        controller: 'comentarioDeleteCtrl'
                    }
                }
        });
        }
    ]);
})(window.angular);
