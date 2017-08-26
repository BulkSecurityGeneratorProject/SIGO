(function() {
    'use strict';

    angular
        .module('sigoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('insumos', {
            parent: 'entity',
            url: '/insumos',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.insumos.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/insumos/insumos.html',
                    controller: 'InsumosController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('insumos');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('insumos-detail', {
            parent: 'insumos',
            url: '/insumos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.insumos.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/insumos/insumos-detail.html',
                    controller: 'InsumosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('insumos');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Insumos', function($stateParams, Insumos) {
                    return Insumos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'insumos',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('insumos-detail.edit', {
            parent: 'insumos-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insumos/insumos-dialog.html',
                    controller: 'InsumosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Insumos', function(Insumos) {
                            return Insumos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('insumos.new', {
            parent: 'insumos',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insumos/insumos-dialog.html',
                    controller: 'InsumosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                marca: null,
                                descripcion: null,
                                categoria: null,
                                observaciones: null,
                                codigo: null,
                                stock: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('insumos', null, { reload: 'insumos' });
                }, function() {
                    $state.go('insumos');
                });
            }]
        })
        .state('insumos.edit', {
            parent: 'insumos',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insumos/insumos-dialog.html',
                    controller: 'InsumosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Insumos', function(Insumos) {
                            return Insumos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('insumos', null, { reload: 'insumos' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('insumos.delete', {
            parent: 'insumos',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/insumos/insumos-delete-dialog.html',
                    controller: 'InsumosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Insumos', function(Insumos) {
                            return Insumos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('insumos', null, { reload: 'insumos' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
