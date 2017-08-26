(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('PedidoDetailController', PedidoDetailController);

    PedidoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Pedido', 'Proveedor', 'FacturaCompra', 'DetallePedido'];

    function PedidoDetailController($scope, $rootScope, $stateParams, previousState, entity, Pedido, Proveedor, FacturaCompra, DetallePedido) {
        var vm = this;

        vm.pedido = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sigoApp:pedidoUpdate', function(event, result) {
            vm.pedido = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
