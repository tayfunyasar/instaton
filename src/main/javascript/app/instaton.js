require('angular-translate');
require('angular-sanitize');
require('angular-dynamic-locale');
require('angular-ui-router');
require('angular-ui-bootstrap');
require('angular-cookies');
require('angular-translate-interpolation-messageformat');
require('angular-translate-loader-partial');
require('angular-translate-storage-cookie');
require('angular-translate-loader-static-files');
require('angular-cache');
require('ng-device-detector');
require('angular-loading-bar');
require('ng-idle');
require('typeahead.js/dist/typeahead.jquery');
require('moment/locale/tr');
require('ng-infinite-scroll/build/ng-infinite-scroll');
require('angular-moment');
require('angular-ui-mask');
require('bootstrap-daterangepicker/daterangepicker');
require('angular-daterangepicker');
require('@lordfriend/nya-bootstrap-select/dist/js/nya-bs-select');

require('./instaton.app');

angular.module('instaton', [
  'angularMoment',
  'pascalprecht.translate',
  'ngSanitize',
  'tmh.dynamicLocale',
  'ui.router',
  'ngCookies',
  'ng.deviceDetector',
  'ui.bootstrap',
  'angular-loading-bar',
  'infinite-scroll',
  'angular-cache',
  'ngIdle',
  'daterangepicker',
  'ngLocale',
  'nya.bootstrap.select',
  'ui.mask',

  'instaton.app',
]);

config.$inject = [
  '$translatePartialLoaderProvider',
  '$locationProvider',
  '$translateProvider',
  'tmhDynamicLocaleProvider',
  '$urlRouterProvider',
  'cfpLoadingBarProvider',
  'CacheFactoryProvider',
  'TitleProvider',
  '$httpProvider',
  'IdleProvider',
  'KeepaliveProvider',
  'nyaBsConfigProvider',
];

function config(
  $translatePartialLoaderProvider,
  $locationProvider,
  $translateProvider,
  tmhDynamicLocaleProvider,
  $urlRouterProvider,
  cfpLoadingBarProvider,
  CacheFactoryProvider,
  TitleProvider,
  $httpProvider,
  IdleProvider,
  KeepaliveProvider,
  nyaBsConfigProvider
) {
  $locationProvider.html5Mode(true);

  $httpProvider.interceptors.push('MainHttpInterceptorFactory');

  $urlRouterProvider.otherwise('/');

  // translate
  $translateProvider.useLoader('$translatePartialLoader', {
    urlTemplate: 'assets/translate/{lang}/{part}.json',
  });

  $translateProvider.preferredLanguage('tr');
  // $translateProvider.useStorage('translationStorageProvider');
  $translateProvider.useSanitizeValueStrategy(null);
  $translateProvider.addInterpolation('$translateMessageFormatInterpolation');

  tmhDynamicLocaleProvider.defaultLocale('tr');
  tmhDynamicLocaleProvider.localeLocationPattern('assets/translate/angular-locale_{{locale}}.js');
  tmhDynamicLocaleProvider.useCookieStorage();
  tmhDynamicLocaleProvider.storageKey('NG_TRANSLATE_LANG_KEY');

  $translatePartialLoaderProvider.addPart('common');

  // loading
  cfpLoadingBarProvider.spinnerTemplate = "\
  <div class='spinner-bg'> \
    <section class='wrapper'>\
	  <div class='uil-default-css' style='transform:scale(0.65);'>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(0deg) translate(0,-60px);transform:rotate(0deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(30deg) translate(0,-60px);transform:rotate(30deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(60deg) translate(0,-60px);transform:rotate(60deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(90deg) translate(0,-60px);transform:rotate(90deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(120deg) translate(0,-60px);transform:rotate(120deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(150deg) translate(0,-60px);transform:rotate(150deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(180deg) translate(0,-60px);transform:rotate(180deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(210deg) translate(0,-60px);transform:rotate(210deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(240deg) translate(0,-60px);transform:rotate(240deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(270deg) translate(0,-60px);transform:rotate(270deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(300deg) translate(0,-60px);transform:rotate(300deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    <div style='top:77px;left:91px;width:18px;height:46px;background:#00743a;-webkit-transform:rotate(330deg) translate(0,-60px);transform:rotate(330deg) translate(0,-60px);border-radius:8px;position:absolute;'></div>\
    </div>\
    </section>\
    </div>\
    ";
  cfpLoadingBarProvider.latencyThreshold = 0;
  cfpLoadingBarProvider.includeBar = true;
  cfpLoadingBarProvider.parentSelector = '#loading-bar-container';

  // Cache Defaults
  angular.extend(CacheFactoryProvider.defaults, {
    maxAge: 60 * 60 * 1000, // 1 Saat
    deleteOnExpire: 'aggressive',
    storageMode: 'localStorage',
    storagePrefix: 'gt_',
  });

  IdleProvider.idle(13 * 60); // kaç saniye sonra modal çıksın?
  IdleProvider.timeout(2 * 60); // kaç saniye sonra logout edilecek?
  KeepaliveProvider.interval(2 * 60); // kaç saniyede 1 sessionExtend çağrılacak?
  IdleProvider.autoResume('notIdle');
  IdleProvider.interrupt('click');
  // ngIdle kütüphanesinin document.title i değiştirmesini kapattık.
  TitleProvider.enabled(false);

  // bootstrap nyaBsConfiguration
  nyaBsConfigProvider.setLocalizedText('tr', {
    defaultNoneSelection: 'Seçiniz',
    noSearchResult: 'Sonuç Bulunamadı',
    numberItemSelected: 'İşyeri (%d)',
  });
  nyaBsConfigProvider.useLocale('tr');
}
angular.module('instaton').config(config);
