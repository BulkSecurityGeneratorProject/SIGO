'use strict';

describe('Controller Tests', function() {

    describe('Proveedor Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockProveedor, MockPedido, MockFacturaCompra;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockProveedor = jasmine.createSpy('MockProveedor');
            MockPedido = jasmine.createSpy('MockPedido');
            MockFacturaCompra = jasmine.createSpy('MockFacturaCompra');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Proveedor': MockProveedor,
                'Pedido': MockPedido,
                'FacturaCompra': MockFacturaCompra
            };
            createController = function() {
                $injector.get('$controller')("ProveedorDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sigoApp:proveedorUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
