(function(ng){
    var mod = ng.module("blogsModule");
    mod.controller("blogsUpdateCtrl",["$scope","$rootScope","$http","blogsContext","$state","$filter",
    
    
    
    
    
    function ($scope, $rootScope, $http, blogsContext, $state, $filter) {
            $rootScope.edit = true;
            
            
            id = $state.params.blogId;
            
           if ($state.params.blogId !== null && $state.params.blogId !== undefined) 
           {  
            $http.get(blogsContext+"/"+id).then(function(response){
                var blog = response.data;
                $scope.tema = blog.tema;
                $scope.descripcion =blog.descripcion;
                
                
                
            });
        }
    
    
            $scope.createBlog = function () {
                
                $http.put(blogsContext, { 
                    tema: $scope.tema,
                    descripcion:$scope.descripcion,
                    likes:$scope.likes,
                    id:id}
                    ).then(function (response) {
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
    
    
    
    
    
    
    
    ]);
})(window.angular);

