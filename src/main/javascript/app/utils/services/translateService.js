TranslateService.$inject = ['$translate'];

function TranslateService($translate) {
  var service = {
    getTranslateValue: getTranslateValue,
  };

  return service;

  function getTranslateValue(key) {
    return $translate(key).then(function (translatedValue) {
      return translatedValue;
    });
  }
}

angular.module('instaton.app.utils').service('TranslateService', TranslateService);
