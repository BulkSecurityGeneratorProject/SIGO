'use strict';

describe('Controller Tests', function() {

    describe('DetallePedido Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockDetallePedido, MockPedido, MockInsumos;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockDetallePedido = jasmine.createSpy('MockDetallePedido');
            MockPedido = jasmine.createSpy('MockPedido');
            MockInsumos = jasmine.createSpy('MockInsumos');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'DetallePedido': MockDetallePedido,
                'Pedido': MockPedido,
                'Insumos': MockInsumos
            };
            createController = function() {
                $injector.get('$controller')("DetallePedidoDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'sigoApp:detallePedidoUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
