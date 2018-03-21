TwitterUserService.$inject = ['TwitterUserFactory'];

function TwitterUserService(TwitterUserFactory) {
  var self = this;

  self.add = function (postData) {
    return TwitterUserFactory.add(postData);
  };

  self.list = function () {
    return TwitterUserFactory.list();
  };

  self.delete = function (postData) {
    return TwitterUserFactory.delete(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('TwitterUserService', TwitterUserService);