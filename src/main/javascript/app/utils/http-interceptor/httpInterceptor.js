function processModal($inject, rdata) {
  var modalService = $inject.get('ModalService');
  if (rdata.returnKey == 'SESSION_EXPIRED' || rdata.returnKey == 'INVALID_STOKEN' || rdata.returnKey == 'PARALLEL_SERVICE_ERROR') {
    modalService.openLogoutModal(rdata);
  } else {
    modalService.openFailureModal(rdata);
  }
}

function guid() {
  function s4() {
    return Math.floor((1 + Math.random()) * 0x10000)
      .toString(16)
      .substring(1);
  }
  return s4() + s4() + s4() + s4() + s4() + s4() + s4() + s4();
}

HttpInterceptor.$inject = ['$q', '$injector', '$rootScope', '$cookies', 'CacheFactory', 'InstatonConfig', '$window'];

function HttpInterceptor($q, $inject, $rootScope, $cookies, cacheFactory, InstatonConfig, $window) {

  return {
    request: function (config) {
      var $state = $inject.get('$state');
      var currentState = $state.current;
      var canceller = $q.defer();
      if (currentState.name == '' || currentState.preventServiceCheck) {
        // canceller.resolve();
        config.timeout = canceller.promise;
        return config;
      }

      var clientId = $cookies.get('clientId');
      var clientIdCache = cacheFactory.get('clientIdCache');
      if (!clientIdCache) {
        cacheFactory('clientIdCache', {
          storageMode: 'localStorage',
          maxAge: 365 * 24 * 60 * 60 * 1000, // Items added to this cache expire after 365 days
        });
        clientIdCache = cacheFactory.get('clientIdCache');
      }
      if (!clientId) {
        clientId = clientIdCache.get('clientId');
        if (!clientId) {
          clientId = 'ETN-' + guid();
          clientIdCache.put('clientId', clientId);
          $cookies.put('clientId', clientId);
        }
      }

      var etrPathname = $state.current.url ? $state.current.url : $window.location.pathname;
      config.headers.etrPathname = etrPathname;
      config.headers.clientId = clientId;
      return config;
    },

    response: function (response) {
      var rdata = response.data || {

      };
      if (rdata.returnKey === 'SERVICE_ERROR') {
        $rootScope.$broadcast('$serviceNotAvailable');
      }
      if (rdata.showInModal) {
        processModal($inject, rdata);
        return $q.reject(response);
      }
      return response;
    },

    responseError: function (response) {
      var rstatus = response.status;
      var rdata = response.data || {

      };

      if (rdata.returnKey === 'SERVICE_ERROR') {
        $rootScope.$broadcast('$serviceNotAvailable');
      }

      if (rstatus === 403) {
        $window.location.pathname = InstatonConfig.LOGIN_URL;
      }

      if (rdata.showInModal) {
        processModal($inject, rdata);
        return $q.reject(response);
      }

      return $q.reject(response);
    }
  };
}

angular.module('instaton.app.utils').factory('MainHttpInterceptorFactory', HttpInterceptor);
