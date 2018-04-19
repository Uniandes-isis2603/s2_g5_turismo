(function (ng) {
    var mod = ng.module("usuariosModule");
    mod.constant("usuariosContext", "api/usuario");
    mod.controller('usuariosCtrl', ['$scope', '$http', 'usuariosContext', '$state',
        function ($scope, $http, usuariosContext, $state) {
            $http.get(usuariosContext).then(function (response) {
                $scope.usuariosRecords = response.data;
            });
        }
    ]);
}
)(window.angular);

