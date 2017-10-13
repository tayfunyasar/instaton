LanguageController.$inject = ['$translate', 'LanguageService', 'tmhDynamicLocale'];

function LanguageController($translate, LanguageService, tmhDynamicLocale) {
  var self = this;

  self.changeLanguage = changeLanguage;
  self.languages = null;

  LanguageService.getAll().then(function (languages) {
    self.languages = languages;
  });

  function changeLanguage(languageKey) {
    $translate.use(languageKey);
    tmhDynamicLocale.set(languageKey);
  }
}

angular.module('instaton.app.utils').controller('LanguageController', LanguageController);
