var _ = require('underscore');

function run($rootScope, $state, StateTranslatorFactory, $location, $cookies, Idle, Keepalive, $document, $window) {
  $rootScope.$on('$stateChangeStart', function (event, toState, params) {

  });

  $rootScope.$on('$stateChangeSuccess', function () {

  });

  // firma-secimi sayfasına back yaparak gidemez.
  $rootScope.$on('$locationChangeStart', function (event, next, current) {

  });

  Idle.setIdle(30); // kaç saniye sonra modal çıksın?
  Idle.setTimeout(120); // kaç saniye sonra logout edilecek?
  Keepalive.setInterval(3);
  Idle.watch();
}

angular.module('instaton.app', [
    'instaton.app.content',
    'instaton.app.header',
    'instaton.app.footer',
    'instaton.app.utils',
  ])
  .run(run)
  .constant('InstatonConfig', {
    GUEST_ENDPOINT_URL: '/api/guest',
  });
