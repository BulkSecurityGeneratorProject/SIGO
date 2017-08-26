(function() {
    'use strict';
    angular
        .module('sigoApp')
        .factory('FacturaCompra', FacturaCompra);

    FacturaCompra.$inject = ['$resource'];

    function FacturaCompra ($resource) {
        var resourceUrl =  'api/factura-compras/:id';

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
