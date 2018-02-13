InstagramMessageService.$inject = ['InstagramMessageFactory'];

function InstagramMessageService(InstagramMessageFactory) {
  var self = this;

  self.list = function () {
    return InstagramMessageFactory.list();
  };

  return self;
}

angular.module('instaton.app.middleware').service('InstagramMessageService', InstagramMessageService);