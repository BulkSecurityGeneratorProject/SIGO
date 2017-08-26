(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetallePedidoDetailController', DetallePedidoDetailController);

    DetallePedidoDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DetallePedido', 'Pedido', 'Insumos'];

    function DetallePedidoDetailController($scope, $rootScope, $stateParams, previousState, entity, DetallePedido, Pedido, Insumos) {
        var vm = this;

        vm.detallePedido = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sigoApp:detallePedidoUpdate', function(event, result) {
            vm.detallePedido = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
