(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('PedidoDialogController', PedidoDialogController);

    PedidoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Pedido', 'Proveedor', 'FacturaCompra', 'DetallePedido'];

    function PedidoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Pedido, Proveedor, FacturaCompra, DetallePedido) {
        var vm = this;

        vm.pedido = entity;
        vm.clear = clear;
        vm.save = save;
        vm.proveedors = Proveedor.query();
        vm.facturacompras = FacturaCompra.query();
        vm.detallepedidos = DetallePedido.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.pedido.id !== null) {
                Pedido.update(vm.pedido, onSaveSuccess, onSaveError);
            } else {
                Pedido.save(vm.pedido, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sigoApp:pedidoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
