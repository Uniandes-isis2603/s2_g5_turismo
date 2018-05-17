/**
 * @ngdoc overview
 * @name blogs.module:blogsModule
 * @description
 * Definición del módulo de Angular de blogs. El módulo encapsula todos los 
 * controladores y los templates HTML que estén relacionados con los blogs 
 * directamente. En la configuración del módulo se injecta la dependencia de 
 * ui.router que es la que se utiliza para la configuración de las URLs bajo las
 * cuales se accede al módulo. Por ejemplo, para mostrar los blogs en la 
 * URL: 'localhost:8080/blogs/list' es necesario configurar el router por 
 * medio del stateProvider que informa a AngularJS de la relación entre la URL, 
 * un estado definido (estado de mostrar blogs), el controlador y la vista 
 * correspondiente. Los estados definidos en este modulo son:
 * ```
 * | ESTADO          | URL                        | VISTAS                 |
 * |-----------------|----------------------------|------------------------|
 * | blogs           | /blogs                     | mainView:              |
 * |                 |                            | blogs.html             |
 * |                 |                            |                        |
 * | blogsList       | /list                      | listView:              |
 * |                 |                            | blogs.list.html        |
 * |                 |                            |                        |
 * | blogsDetail     | /{blogId:int}/detail       | listView:              |
 * |                 |                            | blogs.list.html        |
 * |                 |                            | detailView:            |
 * |                 |                            | blogs.detail.html      |
 * |-----------------|----------------------------|------------------------|
 *```
 */
(function (ng) {
    var mod = ng.module("blogsModule", ['ui.router']);
    mod.constant("blogsContext", "api/blogs");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/blogs/';
            $urlRouterProvider.otherwise("/blogsList");

            $stateProvider.state('blogs', {
                url: '/blogs',
                abstract: true,
                views: {
                    'sideView': {
                        templateUrl: basePath + 'blogs.side.html'
                    },
                    'mainView': {
                        templateUrl: basePath + 'blogs.html',
                        controller: 'blogsCtrl',
                        controllerAs: 'ctrl'
                    }
                    
                    
                }
                
            }).state('blogUpdate', {
                url: '/update/{blogId:int}',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + "blog.create.html",
                        controller: 'blogsUpdateCtrl'
                    },
                   'sideView':{
                       templateUrl: basePath + 'blogs.side.html'
                   }
                }
            }).state('blogsList', {
                url: '/list',
                parent: 'blogs',
                views: {
                    'listView': {
                        templateUrl: basePath + 'blogs.list.html'
                    },
                    'sideView': {
                        templateUrl: basePath + 'blogs.side.html'
                    }
                }
            }).state("blogCreate",{
                       url:"/create",
                       parent:"blogs",
                       views:{
                           'detailView':{
                               templateUrl:basePath +"blog.create.html",
                               controller:"blogNewctrl"
                           },
                           'sideView':{
                               templateUrl: basePath + 'blogs.side.html'
                           }
                       }
                   }).state('blogsDetail', {
                url: '/{blogId:int}/detail',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                  
                    'detailView': {
                        templateUrl: basePath + 'blogs.detail.html',
                        controller: 'blogsDetailCtrl',
                        controllerAs: 'ctrl'
                    },
                    'sideView': {
                        templateUrl: basePath + 'blogs.side.html'
                    }
                    

                }

            }).state('deleteBlog', {
                url: '/delete/{blogId:int}',
                parent: 'blogs',
                param: {
                    blogId: null
                },
                views: {
                    'listView': {
                        templateUrl: basePath + 'blog.delete.html',
                        controller: 'blogDeleteCtrl'
                    }
                }
        });
        }]);
})(window.angular);
