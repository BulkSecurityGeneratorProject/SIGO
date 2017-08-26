(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('InsumosDialogController', InsumosDialogController);

    InsumosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Insumos', 'DetallePedido', 'DetalleFacturaCompra'];

    function InsumosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Insumos, DetallePedido, DetalleFacturaCompra) {
        var vm = this;

        vm.insumos = entity;
        vm.clear = clear;
        vm.save = save;
        vm.detallepedidos = DetallePedido.query();
        vm.detallefacturacompras = DetalleFacturaCompra.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.insumos.id !== null) {
                Insumos.update(vm.insumos, onSaveSuccess, onSaveError);
            } else {
                Insumos.save(vm.insumos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sigoApp:insumosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
