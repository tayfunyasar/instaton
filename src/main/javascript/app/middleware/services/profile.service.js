ProfileService.$inject = ['ProfileFactory'];

function ProfileService(ProfileFactory) {
  var self = this;

  self.getProfile = function (postData) {
    return ProfileFactory.getProfile(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('ProfileService', ProfileService);