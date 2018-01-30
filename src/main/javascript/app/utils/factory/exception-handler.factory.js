// ExceptionHandler.$inject = ['$log', '$injector'];
//
// function ExceptionHandler($log, $injector) {
//   return function myExceptionHandler(exception, cause) {
//     debugger;
//     //  $log.error(exception, cause);
//
//     var errorData = {
//       msg: exception.message,
//       jsUrl: $injector.get('$window').location.href,
//       // line:line,
//       col: cause,
//       stack: exception.stack,
//       url: $injector.get('$window').location.href,
//     };
//
//     var GUEST_ENDPOINT_URL = $injector.get('InstatonConfig').GUEST_ENDPOINT_URL;
//
//     // $injector.get('$http').post(GUEST_ENDPOINT_URL + '/jserrorlogger', errorData);
//   };
// }
// angular.module('instaton.app.utils').factory('$exceptionHandler', ExceptionHandler);