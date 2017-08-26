(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('ProveedorController', ProveedorController);

    ProveedorController.$inject = ['Proveedor'];

    function ProveedorController(Proveedor) {

        var vm = this;

        vm.proveedors = [];

        loadAll();

        function loadAll() {
            Proveedor.query(function(result) {
                vm.proveedors = result;
                vm.searchQuery = null;
            });
        }
    }
})();
