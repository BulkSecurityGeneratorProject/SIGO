(function() {
    'use strict';
    angular
        .module('sigoApp')
        .factory('Insumos', Insumos);

    Insumos.$inject = ['$resource'];

    function Insumos ($resource) {
        var resourceUrl =  'api/insumos/:id';

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
