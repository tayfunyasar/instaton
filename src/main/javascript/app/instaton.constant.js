angular.module('instaton.app', [
    'instaton.app.content',
    'instaton.app.header',
    'instaton.app.footer',
    'instaton.app.utils',
    'instaton.app.template',
  ])
  .constant('InstatonConfig', {
    GUEST_ENDPOINT_URL: '/api/guest',
  });