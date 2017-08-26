(function() {
    'use strict';

    angular
        .module('sigoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('detalle-factura-compra', {
            parent: 'entity',
            url: '/detalle-factura-compra',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.detalleFacturaCompra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/detalle-factura-compra/detalle-factura-compras.html',
                    controller: 'DetalleFacturaCompraController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('detalleFacturaCompra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('detalle-factura-compra-detail', {
            parent: 'detalle-factura-compra',
            url: '/detalle-factura-compra/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.detalleFacturaCompra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/detalle-factura-compra/detalle-factura-compra-detail.html',
                    controller: 'DetalleFacturaCompraDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('detalleFacturaCompra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DetalleFacturaCompra', function($stateParams, DetalleFacturaCompra) {
                    return DetalleFacturaCompra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'detalle-factura-compra',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('detalle-factura-compra-detail.edit', {
            parent: 'detalle-factura-compra-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-factura-compra/detalle-factura-compra-dialog.html',
                    controller: 'DetalleFacturaCompraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DetalleFacturaCompra', function(DetalleFacturaCompra) {
                            return DetalleFacturaCompra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('detalle-factura-compra.new', {
            parent: 'detalle-factura-compra',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-factura-compra/detalle-factura-compra-dialog.html',
                    controller: 'DetalleFacturaCompraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cantidadRecibida: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('detalle-factura-compra', null, { reload: 'detalle-factura-compra' });
                }, function() {
                    $state.go('detalle-factura-compra');
                });
            }]
        })
        .state('detalle-factura-compra.edit', {
            parent: 'detalle-factura-compra',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-factura-compra/detalle-factura-compra-dialog.html',
                    controller: 'DetalleFacturaCompraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DetalleFacturaCompra', function(DetalleFacturaCompra) {
                            return DetalleFacturaCompra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('detalle-factura-compra', null, { reload: 'detalle-factura-compra' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('detalle-factura-compra.delete', {
            parent: 'detalle-factura-compra',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-factura-compra/detalle-factura-compra-delete-dialog.html',
                    controller: 'DetalleFacturaCompraDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DetalleFacturaCompra', function(DetalleFacturaCompra) {
                            return DetalleFacturaCompra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('detalle-factura-compra', null, { reload: 'detalle-factura-compra' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
