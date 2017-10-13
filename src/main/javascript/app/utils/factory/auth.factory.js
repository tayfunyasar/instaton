AuthFactory.$inject = ['$http', '$q', 'CacheFactory', 'GenericService', 'LoginService'];

function AuthFactory($http, $q, cacheFactory, GenericService, LoginService) {
  var cacheName = 'userInfoCache';
  var cacheParamName = 'userInfo';
  var userInfo = null;

  cacheFactory(cacheName, {
    maxAge: 60 * 60 * 1000, // Items added to this cache expire after 60 minutes
    storageMode: 'sessionStorage',
    deleteOnExpire: 'aggressive', // Items will be deleted from this cache when they expire
  });

  function getUserInfoFromCache(useCache) {
    var cache = cacheFactory.get(cacheName);
    var cachedUserInfo = cache.get(cacheParamName);
    if (useCache && cachedUserInfo) {
      setUserInfo(cachedUserInfo);
      return cachedUserInfo;
    }
    return null;
  }

  function getUserInfoFromService() {
    return GenericService.getCurrentFirmDetail().then(function (response) {
      setUserInfo(response.data);
    });
  }

  function setUserInfo(_userInfo) {
    if (_userInfo) {
      userInfo = angular.copy(_userInfo);
    }
    var cache = cacheFactory.get(cacheName);
    if (cache && userInfo) {
      cache.put(cacheParamName, userInfo);
    }
  }

  var factory = {
    disabled: false,
    getUserInfo: function () {
      return getUserInfoFromCache(true);
    },
    fetchUserInfo: function (useCache) {
      var fromCache = true;

      if (angular.isDefined(useCache)) {
        fromCache = useCache;
      }

      var cachedRoles = getUserInfoFromCache(fromCache);

      if (!cachedRoles) {
        return getUserInfoFromService();
      }
      return cachedRoles;
    },
  };
  return factory;
}
angular.module('instaton.app.utils').factory('AuthFactory', AuthFactory);
