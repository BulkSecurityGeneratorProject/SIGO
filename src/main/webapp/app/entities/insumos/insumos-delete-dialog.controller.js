(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('InsumosDeleteController',InsumosDeleteController);

    InsumosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Insumos'];

    function InsumosDeleteController($uibModalInstance, entity, Insumos) {
        var vm = this;

        vm.insumos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Insumos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
