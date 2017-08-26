'use strict';

describe('Controller Tests', function() {

    describe('Insumos Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockInsumos, MockDetallePedido, MockDetalleFacturaCompra;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockInsumos = jasmine.createSpy('MockInsumos');
            MockDetallePedido = jasmine.createSpy('MockDetallePedido');
            MockDetalleFacturaCompra = jasmine.createSpy('MockDetalleFacturaCompra');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Insumos': MockInsumos,
                'DetallePedido': MockDetallePedido,
                'DetalleFacturaCompra': MockDetalleFacturaCompra
            };
            createController = function() {
                $injector.get('$controller')("InsumosDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sigoApp:insumosUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
