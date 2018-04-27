(function(ng){
    
    var mod = ng.module("blogsModule");
    mod.constant("blogcontext","api/blogs");
   mod.controller('blogNewctrl', ['$scope', '$http', 'blogcontext', '$state', '$rootScope',
       
        function($scope, $http, blogcontext, $state, $rootScope){
        
            $rootScope.edit = false;

            
            alert("Create")
           
            $scope.createBlog = function () {
                
                $http.post(blogcontext, { 
                    tema: $scope.tema,
                    descripcion:$scope.descripcion,
                    likes:0}
                    ).then(function (response) {
                    $state.go('blogsList', {blogId: response.data.id}, {reload: true});
                });
            };
        }
        ]);
    })(window.angular);