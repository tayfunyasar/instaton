config.$inject = ['$stateProvider'];

function config($stateProvider) {
  $stateProvider.state('content', {
    templateUrl: 'content/tpl.html',
    controller: 'ContentController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.login', {
    url: '/login',
    templateUrl: 'content/login/tpl.html',
    controller: 'LoginController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.dashboard', {
    url: '/',
    templateUrl: 'content/dashboard/twitter/tpl.html',
    controller: 'DashboardTwitterController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.dashboard.instagram', {
    url: '/',
    templateUrl: 'content/dashboard/instagram/tpl.html',
    controller: 'DashboardInstagramController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.messages', {
    url: '/ss',
    templateUrl: 'content/messages/tpl.html',
    controller: 'MessagesController',
    controllerAs: 'self',
  });
}

angular.module('instaton.app.content', []).config(config);