(function() {
    'use strict';
    angular
        .module('sigoApp')
        .factory('DetalleFacturaCompra', DetalleFacturaCompra);

    DetalleFacturaCompra.$inject = ['$resource'];

    function DetalleFacturaCompra ($resource) {
        var resourceUrl =  'api/detalle-factura-compras/:id';

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
