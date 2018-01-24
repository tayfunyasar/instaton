config.$inject = ['$stateProvider'];

function config($stateProvider) {
  $stateProvider.state('content', {
    templateUrl: 'content/tpl.html',
    controller: 'ContentController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.login', {
    url: '/',
    templateUrl: 'content/login/tpl.html',
    controller: 'LoginController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.dashboard', {
    url: '/dashboard',
    templateUrl: 'content/dashboard/tpl.html',
    controller: 'DashboardController',
    controllerAs: 'self',
  });
}

angular.module('instaton.app.content', []).config(config);