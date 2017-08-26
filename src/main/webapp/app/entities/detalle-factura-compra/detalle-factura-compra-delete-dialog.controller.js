(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetalleFacturaCompraDeleteController',DetalleFacturaCompraDeleteController);

    DetalleFacturaCompraDeleteController.$inject = ['$uibModalInstance', 'entity', 'DetalleFacturaCompra'];

    function DetalleFacturaCompraDeleteController($uibModalInstance, entity, DetalleFacturaCompra) {
        var vm = this;

        vm.detalleFacturaCompra = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DetalleFacturaCompra.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
