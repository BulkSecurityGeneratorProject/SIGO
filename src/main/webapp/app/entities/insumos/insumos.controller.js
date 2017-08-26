(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('InsumosController', InsumosController);

    InsumosController.$inject = ['Insumos'];

    function InsumosController(Insumos) {

        var vm = this;

        vm.insumos = [];

        loadAll();

        function loadAll() {
            Insumos.query(function(result) {
                vm.insumos = result;
                vm.searchQuery = null;
            });
        }
    }
})();
