'use strict';

describe('Controller Tests', function() {

    describe('Pedido Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockPedido, MockProveedor, MockFacturaCompra, MockDetallePedido;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockPedido = jasmine.createSpy('MockPedido');
            MockProveedor = jasmine.createSpy('MockProveedor');
            MockFacturaCompra = jasmine.createSpy('MockFacturaCompra');
            MockDetallePedido = jasmine.createSpy('MockDetallePedido');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Pedido': MockPedido,
                'Proveedor': MockProveedor,
                'FacturaCompra': MockFacturaCompra,
                'DetallePedido': MockDetallePedido
            };
            createController = function() {
                $injector.get('$controller')("PedidoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sigoApp:pedidoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
