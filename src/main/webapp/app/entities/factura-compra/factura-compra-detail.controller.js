(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('FacturaCompraDetailController', FacturaCompraDetailController);

    FacturaCompraDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'FacturaCompra', 'Proveedor', 'Pedido', 'DetalleFacturaCompra'];

    function FacturaCompraDetailController($scope, $rootScope, $stateParams, previousState, entity, FacturaCompra, Proveedor, Pedido, DetalleFacturaCompra) {
        var vm = this;

        vm.facturaCompra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sigoApp:facturaCompraUpdate', function(event, result) {
            vm.facturaCompra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
