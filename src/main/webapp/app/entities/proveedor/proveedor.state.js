(function() {
    'use strict';

    angular
        .module('sigoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('proveedor', {
            parent: 'entity',
            url: '/proveedor',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.proveedor.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/proveedor/proveedors.html',
                    controller: 'ProveedorController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('proveedor');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('proveedor-detail', {
            parent: 'proveedor',
            url: '/proveedor/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'sigoApp.proveedor.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/proveedor/proveedor-detail.html',
                    controller: 'ProveedorDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('proveedor');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Proveedor', function($stateParams, Proveedor) {
                    return Proveedor.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'proveedor',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('proveedor-detail.edit', {
            parent: 'proveedor-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proveedor/proveedor-dialog.html',
                    controller: 'ProveedorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Proveedor', function(Proveedor) {
                            return Proveedor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('proveedor.new', {
            parent: 'proveedor',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proveedor/proveedor-dialog.html',
                    controller: 'ProveedorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                razonSocial: null,
                                ruc: null,
                                direccion: null,
                                correoelectronico: null,
                                telefono: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('proveedor', null, { reload: 'proveedor' });
                }, function() {
                    $state.go('proveedor');
                });
            }]
        })
        .state('proveedor.edit', {
            parent: 'proveedor',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proveedor/proveedor-dialog.html',
                    controller: 'ProveedorDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Proveedor', function(Proveedor) {
                            return Proveedor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('proveedor', null, { reload: 'proveedor' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('proveedor.delete', {
            parent: 'proveedor',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/proveedor/proveedor-delete-dialog.html',
                    controller: 'ProveedorDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Proveedor', function(Proveedor) {
                            return Proveedor.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('proveedor', null, { reload: 'proveedor' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
