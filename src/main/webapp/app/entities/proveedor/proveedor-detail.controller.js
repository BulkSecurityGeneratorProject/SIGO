(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('ProveedorDetailController', ProveedorDetailController);

    ProveedorDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Proveedor', 'Pedido', 'FacturaCompra'];

    function ProveedorDetailController($scope, $rootScope, $stateParams, previousState, entity, Proveedor, Pedido, FacturaCompra) {
        var vm = this;

        vm.proveedor = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sigoApp:proveedorUpdate', function(event, result) {
            vm.proveedor = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
