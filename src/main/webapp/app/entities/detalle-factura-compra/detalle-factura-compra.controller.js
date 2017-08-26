(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetalleFacturaCompraController', DetalleFacturaCompraController);

    DetalleFacturaCompraController.$inject = ['DetalleFacturaCompra'];

    function DetalleFacturaCompraController(DetalleFacturaCompra) {

        var vm = this;

        vm.detalleFacturaCompras = [];

        loadAll();

        function loadAll() {
            DetalleFacturaCompra.query(function(result) {
                vm.detalleFacturaCompras = result;
                vm.searchQuery = null;
            });
        }
    }
})();
