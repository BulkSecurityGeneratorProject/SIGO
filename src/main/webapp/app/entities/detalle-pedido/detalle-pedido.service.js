(function() {
    'use strict';
    angular
        .module('sigoApp')
        .factory('DetallePedido', DetallePedido);

    DetallePedido.$inject = ['$resource'];

    function DetallePedido ($resource) {
        var resourceUrl =  'api/detalle-pedidos/:id';

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
