(function() {
    'use strict';

    angular
        .module('sigoApp')
        .controller('DetalleFacturaCompraDetailController', DetalleFacturaCompraDetailController);

    DetalleFacturaCompraDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DetalleFacturaCompra', 'Insumos', 'FacturaCompra'];

    function DetalleFacturaCompraDetailController($scope, $rootScope, $stateParams, previousState, entity, DetalleFacturaCompra, Insumos, FacturaCompra) {
        var vm = this;

        vm.detalleFacturaCompra = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('sigoApp:detalleFacturaCompraUpdate', function(event, result) {
            vm.detalleFacturaCompra = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
