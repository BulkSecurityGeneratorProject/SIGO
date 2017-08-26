(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('FacturaCompraController', FacturaCompraController);

    FacturaCompraController.$inject = ['FacturaCompra'];

    function FacturaCompraController(FacturaCompra) {

        var vm = this;

        vm.facturaCompras = [];

        loadAll();

        function loadAll() {
            FacturaCompra.query(function(result) {
                vm.facturaCompras = result;
                vm.searchQuery = null;
            });
        }
    }
})();
