(function(ng){
    var mod = ng.module("comentariosModule");
    mod.controller("comentarioUpdateCtrl",["$scope","$rootScope","$http","comentarioscontext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, comentariosContext, $state, $filter) {
            $rootScope.edit = true;
            
            
            id = $state.params.blogId;
            
            
           if ($state.params.comentarioId !== null && $state.params.comentarioId !== undefined) 
           {  
            $http.get('api/blogs/'+ $state.params.blogId + comentariosContext + '/'+ $state.params.comentarioId).then(function(response){
                var comentario = response.data;
              
                $scope.comentario = comentario.comentario;
               
                
                
                
            });
        }
    
    
            $scope.createComentario = function () {
                
                $http.put('api/blogs/'+ $state.params.blogId + comentariosContext, { 
                    comentario: $scope.comentario,
                    id: $state.params.comentarioId
                    }
                    ).then(function (response) {
                    $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

