function run($rootScope, $state, $location, $cookies, Idle, Keepalive, ParameterService, $document, GenericService, $window, PartnerConfig, $timeout, GuestFactory) {
  $rootScope.$on('$stateChangeStart', function (event, toState, params) {
    if (toState.redirectTo) {
      event.preventDefault();
      $state.go(toState.redirectTo, params, {
        location: 'replace',
      });
    }

    if (($location.$$path != '/error/service-not-available') && ($location.$$path != '/error/un-authorized')) {
      ParameterService.getParameterList(ParameterService.postData.SummaryReportData).then(function (response) {
        var parameterCode = toState.sitemap && toState.sitemap.parameterCode ? toState.sitemap.parameterCode : null;
        if (parameterCode) {
          event.preventDefault();
          // var parameter = _.find(response, 'parameterCode', parameterCode);
          // toState.sitemap.title = parameter.parameterValue;
          $state.go(toState.name);
        }
      });
    }

    if (toState.preventWindowNameCheck) {
      return;
    }

    GuestFactory.getSessionInfo().then(function (response) {
      var sessionId = $window.name;

      if (sessionId != '' && response.data != sessionId) {
        $window.location.pathname = PartnerConfig.PORTAL_URL;
      }
    });
  });

  angular.element(window).resize(function () {
    calculateMinHeight();
  });

  $rootScope.$on('$stateChangeSuccess', function () {
    $timeout(function () {
      calculateMinHeight();
      angular.element('#reportPageLoadingBarContainerTMP').attr('id', 'reportPageLoadingBarContainer');
    }, 1000);
  });

  function calculateMinHeight() {
    angular.element('.dashboard').removeAttr('style');
    if (angular.element(window).width() < 1440) {
      angular.element('.dashboard').css('min-height', angular.element('body').height() * 1.25 + 'px');
    } else {
      angular.element('.dashboard').css('min-height', angular.element('body').height() + 'px');
    }
  }

  // firma-secimi sayfasına back yaparak gidemez.
  $rootScope.$on('$locationChangeStart', function (event, next, current) {
    if (next != current && $location.$$path == '/firma-secimi') {
      event.preventDefault();
    }
  });

  if (($location.$$path != '/error/service-not-available') && ($location.$$path != '/error/un-authorized')) {

    var warningTime = parseInt($cookies.get('warningTime'));
    var expiryTime = ($cookies.get('expiryTime') - $cookies.get('serverTime')) / 1000;
    var idleTime = expiryTime - warningTime;

    Idle.setIdle(idleTime); // kaç saniye sonra modal çıksın?
    // console.log('timeout = ' + warningTime); // 120
    Idle.setTimeout(warningTime); // kaç saniye sonra logout edilecek?
    Keepalive.setInterval(expiryTime - warningTime / 2);
    Idle.watch();
  }

  $timeout(function () {
    if (jQuery('body').hasClass('browser-ie')) {
      jQuery('html').addClass('ie');
    }
  }, 0);
}

angular.module('instaton.app', []).run(run);
