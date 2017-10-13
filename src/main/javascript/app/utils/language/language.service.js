LanguageService.$inject = ['$q', '$http', '$translate', 'LANGUAGES', 'tmhDynamicLocale'];

function LanguageService($q, $http, $translate, LANGUAGES, tmhDynamicLocale) {
  var service = {
    getAll: getAll,
    getCurrent: getCurrent,
    changeLanguage: changeLanguage
  };

  return service;

  function changeLanguage(languageKey) {
    $translate.use(languageKey);
    tmhDynamicLocale.set(languageKey);
  }

  function getAll() {
    var deferred = $q.defer();
    deferred.resolve(LANGUAGES);
    return deferred.promise;
  }

  function getCurrent() {
    var deferred = $q.defer();
    var language = $translate.storage().get('NG_TRANSLATE_LANG_KEY');

    deferred.resolve(language);

    return deferred.promise;
  }
}
angular.module('instaton.app.utils').factory('LanguageService', LanguageService);
