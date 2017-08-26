'use strict';

describe('Controller Tests', function() {

    describe('DetalleFacturaCompra Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDetalleFacturaCompra, MockInsumos, MockFacturaCompra;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDetalleFacturaCompra = jasmine.createSpy('MockDetalleFacturaCompra');
            MockInsumos = jasmine.createSpy('MockInsumos');
            MockFacturaCompra = jasmine.createSpy('MockFacturaCompra');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DetalleFacturaCompra': MockDetalleFacturaCompra,
                'Insumos': MockInsumos,
                'FacturaCompra': MockFacturaCompra
            };
            createController = function() {
                $injector.get('$controller')("DetalleFacturaCompraDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sigoApp:detalleFacturaCompraUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
