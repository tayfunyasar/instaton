function FooterController() {

}

angular.module('instaton.app.footer', []).directive('instatonFooter', function () {
  return {
    restrict: 'E',
    replace: true,
    link: function (scope, elem, attr) {},
    controller: FooterController,
    controllerAs: 'self',
    bindToController: true,
    scope: {

    },
    templateUrl: 'footer/tpl.html',
  };
});