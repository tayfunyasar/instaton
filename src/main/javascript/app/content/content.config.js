config.$inject = ['$stateProvider'];

function config($stateProvider) {
  $stateProvider.state('content', {
    url: '/content',
    templateUrl: './tpl.html',
    controller: 'ContentController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.dashboard', {
    url: '/dashboard',
    templateUrl: './content-dashboard/tpl.html',
    controller: 'ContentDashboardController',
    controllerAs: 'self',
  });
}

angular.module('instaton.app.content', []).config(config);
