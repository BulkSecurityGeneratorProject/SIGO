(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetallePedidoController', DetallePedidoController);

    DetallePedidoController.$inject = ['DetallePedido'];

    function DetallePedidoController(DetallePedido) {

        var vm = this;

        vm.detallePedidos = [];

        loadAll();

        function loadAll() {
            DetallePedido.query(function(result) {
                vm.detallePedidos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
