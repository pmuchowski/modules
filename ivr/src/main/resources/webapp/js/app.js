(function () {
    'use strict';

    /* App Module */

    angular.module('ivr', ['motech-dashboard', 'ivr.controllers', 'ngCookies', 'ui.bootstrap', 'ngRoute',
        'ngSanitize']).config(
    ['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
                when('/ivr', {templateUrl: '../ivr/resources/partials/settings.html',
                    controller: 'SettingsController'}).
                when('/ivr/settings', {templateUrl: '../ivr/resources/partials/settings.html',
                controller: 'SettingsController'});
    }]);
}());
