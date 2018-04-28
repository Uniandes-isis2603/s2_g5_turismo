(function(ng){
    
    var mod = ng.module("comentariosModule");
    mod.constant("comentarioscontext","/comentarios");
   mod.controller('comentarioNewctrl', ['$scope', '$http', 'comentarioscontext', '$state', '$rootScope',
       
        function($scope, $http, comentarioscontext, $state, $rootScope){
            
            
        
            $rootScope.edit = false;

          

           
            $scope.createComentario = function () {
                
                $http.post('api/blogs/'+ $state.params.blogId + comentarioscontext, { 
                    comentario: $scope.comentario
                    },
                     alert('entro'),        
                    ).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
        ]);
    })(window.angular);