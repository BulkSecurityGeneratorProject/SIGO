(function() {
    'use strict';

    angular
        .module('sigoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('detalle-pedido', {
            parent: 'entity',
            url: '/detalle-pedido',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.detallePedido.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/detalle-pedido/detalle-pedidos.html',
                    controller: 'DetallePedidoController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('detallePedido');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('detalle-pedido-detail', {
            parent: 'detalle-pedido',
            url: '/detalle-pedido/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.detallePedido.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/detalle-pedido/detalle-pedido-detail.html',
                    controller: 'DetallePedidoDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('detallePedido');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DetallePedido', function($stateParams, DetallePedido) {
                    return DetallePedido.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'detalle-pedido',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('detalle-pedido-detail.edit', {
            parent: 'detalle-pedido-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-pedido/detalle-pedido-dialog.html',
                    controller: 'DetallePedidoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DetallePedido', function(DetallePedido) {
                            return DetallePedido.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('detalle-pedido.new', {
            parent: 'detalle-pedido',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-pedido/detalle-pedido-dialog.html',
                    controller: 'DetallePedidoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                cantidadPedida: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('detalle-pedido', null, { reload: 'detalle-pedido' });
                }, function() {
                    $state.go('detalle-pedido');
                });
            }]
        })
        .state('detalle-pedido.edit', {
            parent: 'detalle-pedido',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-pedido/detalle-pedido-dialog.html',
                    controller: 'DetallePedidoDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DetallePedido', function(DetallePedido) {
                            return DetallePedido.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('detalle-pedido', null, { reload: 'detalle-pedido' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('detalle-pedido.delete', {
            parent: 'detalle-pedido',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/detalle-pedido/detalle-pedido-delete-dialog.html',
                    controller: 'DetallePedidoDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DetallePedido', function(DetallePedido) {
                            return DetallePedido.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('detalle-pedido', null, { reload: 'detalle-pedido' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
