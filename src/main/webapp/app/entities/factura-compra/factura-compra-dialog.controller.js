(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('FacturaCompraDialogController', FacturaCompraDialogController);

    FacturaCompraDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'FacturaCompra', 'Proveedor', 'Pedido', 'DetalleFacturaCompra'];

    function FacturaCompraDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, FacturaCompra, Proveedor, Pedido, DetalleFacturaCompra) {
        var vm = this;

        vm.facturaCompra = entity;
        vm.clear = clear;
        vm.save = save;
        vm.proveedors = Proveedor.query();
        vm.pedidos = Pedido.query();
        vm.detallefacturacompras = DetalleFacturaCompra.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.facturaCompra.id !== null) {
                FacturaCompra.update(vm.facturaCompra, onSaveSuccess, onSaveError);
            } else {
                FacturaCompra.save(vm.facturaCompra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sigoApp:facturaCompraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
