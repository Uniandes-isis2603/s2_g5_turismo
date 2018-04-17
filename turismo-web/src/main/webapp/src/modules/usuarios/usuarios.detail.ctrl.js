(function (ng) {
    var mod = ng.module("usuariosModule");
    mod.constant("usuariosContext", "api/usuarios");
    mod.controller('usuariosDetailCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {           
            if (($state.params.usuariosId !== undefined)&& ($state.params.usuariosId !== null)) {
                $http.get(usuariosContext + '/' + $state.params.usuariosId).then(function (response) {
                    $scope.currentUsuario = response.data;
                });
            }
        }
    ]);
}
)(window.angular);
            