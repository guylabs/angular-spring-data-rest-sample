'use strict';

var app = angular.module('spring-data-rest-sample', ['ui.bootstrap', 'ngResource', 'ngRoute', 'hljs', 'spring-data-rest'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'partials/main.html'
            }).when('/samples', {
                templateUrl: 'partials/samples.html'
            }).when('/samples/list-embedded-items', {
                templateUrl: 'partials/samples/list-embedded-items.html'
            }).when('/samples/the-resources-method', {
                templateUrl: 'partials/samples/the-resources-method.html'
            }).when('/samples/automatic-link-fetching', {
                templateUrl: 'partials/samples/automatic-link-fetching.html'
            }).otherwise({
                redirectTo: '/'
            });
    }])
    .controller('NavigationController', function ($scope, $location) {
        $scope.navigateTo = function (path) {
            $location.path(path);
        };
        $scope.isCodeCollapsed = true;
        $scope.isResponseCollapsed = true;
        $scope.isProcessedResponseCollapsed = true;
    })
    .controller('SamplesListEmbeddedItemsController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/rest/categories').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.processWithPromise(httpPromise).then(function (processedResponse) {
            $scope.categories = processedResponse._embeddedItems;
            $scope.processedResponse = angular.toJson(processedResponse, true);
        });
    })
    .controller('SamplesAutomaticLinkFetchingController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/rest/categories').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.processWithPromise(httpPromise, 'parent', true).then(function (processedResponse) {
            $scope.categories = processedResponse._embeddedItems;
            $scope.processedResponse = angular.toJson(processedResponse, true);
        });
    })
    .controller('TheResourcesMethodController', function ($scope, $http, SpringDataRestAdapter) {
        var httpPromise = $http.get('/rest/categories').success(function (response) {
            $scope.response = angular.toJson(response, true);
        });

        SpringDataRestAdapter.processWithPromise(httpPromise).then(function (processedResponse) {
            $scope.processedResponse = angular.toJson(processedResponse, true);

            var parentCategoryResource = processedResponse._embeddedItems[1]._resources("parent");
            $scope.parentCategory = parentCategoryResource.get();
        });
    });
