(function() {
    'use strict';
    angular
        .module('sigoApp')
        .factory('Pedido', Pedido);

    Pedido.$inject = ['$resource'];

    function Pedido ($resource) {
        var resourceUrl =  'api/pedidos/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
