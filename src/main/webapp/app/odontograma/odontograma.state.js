(function() {
    'use strict';

    angular
        .module('sigoApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('odontograma', {
            parent: 'app',
            url: '/odontograma',
            views: {
                'content@': {
                    templateUrl: 'app/odontograma/odontograma.html',
                    controller: 'OdontogramaController',
                    controllerAs: 'vm'
                }
            }
        })
    }

})();
