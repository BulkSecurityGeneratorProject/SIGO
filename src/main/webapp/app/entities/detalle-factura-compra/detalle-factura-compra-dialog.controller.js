(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetalleFacturaCompraDialogController', DetalleFacturaCompraDialogController);

    DetalleFacturaCompraDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DetalleFacturaCompra', 'Insumos', 'FacturaCompra'];

    function DetalleFacturaCompraDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DetalleFacturaCompra, Insumos, FacturaCompra) {
        var vm = this;

        vm.detalleFacturaCompra = entity;
        vm.clear = clear;
        vm.save = save;
        vm.insumos = Insumos.query();
        vm.facturacompras = FacturaCompra.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.detalleFacturaCompra.id !== null) {
                DetalleFacturaCompra.update(vm.detalleFacturaCompra, onSaveSuccess, onSaveError);
            } else {
                DetalleFacturaCompra.save(vm.detalleFacturaCompra, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sigoApp:detalleFacturaCompraUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
