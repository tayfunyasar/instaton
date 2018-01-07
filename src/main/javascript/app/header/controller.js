function HeaderController() {

}

angular.module('instaton.app.header', []).directive('instatonHeader', function () {
  return {
    restrict: 'E',
    replace: true,
    link: function (scope, elem, attr) {},
    controller: HeaderController,
    controllerAs: 'self',
    bindToController: true,
    scope: {

    },
    templateUrl: 'assets/tpl/header/tpl.html',
  };
});