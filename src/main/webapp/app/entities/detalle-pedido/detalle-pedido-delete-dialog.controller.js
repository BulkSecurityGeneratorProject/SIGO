(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetallePedidoDeleteController',DetallePedidoDeleteController);

    DetallePedidoDeleteController.$inject = ['$uibModalInstance', 'entity', 'DetallePedido'];

    function DetallePedidoDeleteController($uibModalInstance, entity, DetallePedido) {
        var vm = this;

        vm.detallePedido = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DetallePedido.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
