'use strict';

describe('Controller Tests', function() {

    describe('FacturaCompra Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockFacturaCompra, MockProveedor, MockPedido, MockDetalleFacturaCompra;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockFacturaCompra = jasmine.createSpy('MockFacturaCompra');
            MockProveedor = jasmine.createSpy('MockProveedor');
            MockPedido = jasmine.createSpy('MockPedido');
            MockDetalleFacturaCompra = jasmine.createSpy('MockDetalleFacturaCompra');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'FacturaCompra': MockFacturaCompra,
                'Proveedor': MockProveedor,
                'Pedido': MockPedido,
                'DetalleFacturaCompra': MockDetalleFacturaCompra
            };
            createController = function() {
                $injector.get('$controller')("FacturaCompraDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sigoApp:facturaCompraUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
