(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetallePedidoDialogController', DetallePedidoDialogController);

    DetallePedidoDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'DetallePedido', 'Pedido', 'Insumos'];

    function DetallePedidoDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, DetallePedido, Pedido, Insumos) {
        var vm = this;

        vm.detallePedido = entity;
        vm.clear = clear;
        vm.save = save;
        vm.pedidos = Pedido.query();
        vm.insumos = Insumos.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.detallePedido.id !== null) {
                DetallePedido.update(vm.detallePedido, onSaveSuccess, onSaveError);
            } else {
                DetallePedido.save(vm.detallePedido, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('sigoApp:detallePedidoUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
