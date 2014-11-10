'use strict';

var app = angular.module('spring-data-rest-sample', ['ui.bootstrap', 'ngResource', 'ngRoute', 'hljs', 'spring-data-rest'])
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
                when('/', {
                    templateUrl: 'partials/main.html'
                }).
                when('/samples', {
                    templateUrl: 'partials/samples.html'
                }).
                when('/samples/list-embedded-items', {
                    templateUrl: 'partials/samples/list-embedded-items.html'
                }).otherwise({
                    redirectTo: '/'
                });
        }])
    .controller('NavigationController', function ($scope, $location) {
        $scope.navigateTo = function ( path ) {
            $location.path( path );
        };
        $scope.isCodeCollapsed = true;
    });
