(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('InsumosDetailController', InsumosDetailController);

    InsumosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Insumos', 'DetallePedido', 'DetalleFacturaCompra'];

    function InsumosDetailController($scope, $rootScope, $stateParams, previousState, entity, Insumos, DetallePedido, DetalleFacturaCompra) {
        var vm = this;

        vm.insumos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sigoApp:insumosUpdate', function(event, result) {
            vm.insumos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
