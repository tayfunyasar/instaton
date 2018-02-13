MessagesController.$inject = ['InstagramMessageService', '$state'];

function MessagesController(InstagramMessageService, $state) {
  var self = this;

  self.messages = null;

  self.$onInit = function () {
    InstagramMessageService.list().then(function (response) {
      self.messages = response;
    });
  };

}

angular.module('instaton.app.content').controller('MessagesController', MessagesController);