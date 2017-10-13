IdleController.$inject = ['$scope', 'ModalService', 'InstatonConfig', 'LoginService', 'Idle', 'Keepalive', '$log', '$window'];

function IdleController($scope, ModalService, InstatonConfig, loginService, Idle, Keepalive, $log, $window) {
  var self = this;

  self.modalData = {
    isModalOpen: false,
    countDownText: '02:00',
    beatTheHeart: function () {
      Idle.watch();
      self.modalData.isModalOpen = false;
    },
    logOut: function () {
      $window.location.href = InstatonConfig.LOGIN_URL;
      loginService.getLogout();
    },
    onClose: function () {
      self.modalData.isModalOpen = false;
    }
  };

  $scope.$on('IdleStart', function () {
    // the user appears to have gone idle
    loginService.getBeatTheHeart();
    self.modalData.isModalOpen = true;
    ModalService.customModal('session-expiry.modal.html', self.modalData);
  });

  $scope.$on('IdleWarn', function (e, countdown) {
    // follows after the IdleStart event, but includes a countdown until the user is considered timed out
    // the countdown arg is the number of seconds remaining until then.
    // you can change the title or display a warning dialog from here.
    // you can let them resume their session by calling Idle.watch()

    if (self.modalData.isModalOpen) {
      self.countdown = moment(countdown * 1000).format('mm:ss');
      jQuery('#countDownText').text(self.countdown);
    }
  });

  $scope.$on('IdleTimeout', function () {
    // the user has timed out (meaning idleDuration + timeout has passed without any activity)
    // this is where you'd log them

    if (self.modalData.isModalOpen) {
      self.modalData.logOut();
      Idle.unwatch();
    }
  });

  $scope.$on('IdleEnd', function () {
    // the user has come back from AFK and is doing stuff. if you are warning them, you can use this to hide the dialog
  });

  $scope.$on('Keepalive', function () {
    // do something to keep the user's session alive
    if (!self.modalData.isModalOpen) {
      loginService.getBeatTheHeart();
    } else {
      //
    }
  });

}

angular.module('instaton.app.utils').component('idleTimer', {
  controller: IdleController,
});
