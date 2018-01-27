LoginController.$inject = ['$window', '$state'];

function LoginController($window, $state) {
  var self = this;

  self.redirectToTwitter = function () {
    // $window.location.href = '/connect/twitter';
    $state.go('content.dashboard');
  };

}

angular.module('instaton.app.content').controller('LoginController', LoginController);