(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('FacturaCompraDeleteController',FacturaCompraDeleteController);

    FacturaCompraDeleteController.$inject = ['$uibModalInstance', 'entity', 'FacturaCompra'];

    function FacturaCompraDeleteController($uibModalInstance, entity, FacturaCompra) {
        var vm = this;

        vm.facturaCompra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            FacturaCompra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
