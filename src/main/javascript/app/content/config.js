config.$inject = ['$stateProvider'];

function config($stateProvider) {
  $stateProvider.state('content', {
    templateUrl: 'content/tpl.html',
    controller: 'ContentController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.login', {
    url: '/ss',
    templateUrl: 'content/login/tpl.html',
    controller: 'LoginController',
    controllerAs: 'self',
  });

  $stateProvider.state('content.dashboard', {
    url: '/',
    templateUrl: 'content/dashboard/tpl0.html',
    controller: 'DashboardController',
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