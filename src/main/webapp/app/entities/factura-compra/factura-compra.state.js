(function() {
    'use strict';

    angular
        .module('sigoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('factura-compra', {
            parent: 'entity',
            url: '/factura-compra',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.facturaCompra.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/factura-compra/factura-compras.html',
                    controller: 'FacturaCompraController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('facturaCompra');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('factura-compra-detail', {
            parent: 'factura-compra',
            url: '/factura-compra/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.facturaCompra.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/factura-compra/factura-compra-detail.html',
                    controller: 'FacturaCompraDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('facturaCompra');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'FacturaCompra', function($stateParams, FacturaCompra) {
                    return FacturaCompra.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'factura-compra',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('factura-compra-detail.edit', {
            parent: 'factura-compra-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factura-compra/factura-compra-dialog.html',
                    controller: 'FacturaCompraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FacturaCompra', function(FacturaCompra) {
                            return FacturaCompra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('factura-compra.new', {
            parent: 'factura-compra',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factura-compra/factura-compra-dialog.html',
                    controller: 'FacturaCompraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                costo: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('factura-compra', null, { reload: 'factura-compra' });
                }, function() {
                    $state.go('factura-compra');
                });
            }]
        })
        .state('factura-compra.edit', {
            parent: 'factura-compra',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factura-compra/factura-compra-dialog.html',
                    controller: 'FacturaCompraDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['FacturaCompra', function(FacturaCompra) {
                            return FacturaCompra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('factura-compra', null, { reload: 'factura-compra' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('factura-compra.delete', {
            parent: 'factura-compra',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/factura-compra/factura-compra-delete-dialog.html',
                    controller: 'FacturaCompraDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['FacturaCompra', function(FacturaCompra) {
                            return FacturaCompra.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('factura-compra', null, { reload: 'factura-compra' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
