InstagramUserService.$inject = ['InstagramUserFactory'];

function InstagramUserService(InstagramUserFactory) {
  var self = this;

  self.add = function (postData) {
    return InstagramUserFactory.add(postData);
  };

  self.list = function () {
    return InstagramUserFactory.list();
  };

  self.delete = function (postData) {
    return InstagramUserFactory.delete(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('InstagramUserService', InstagramUserService);