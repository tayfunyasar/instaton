ProfileService.$inject = ['ProfileFactory'];

function ProfileService(ProfileFactory) {
  var self = this;

  self.getProfile = function (postData) {
    return ProfileFactory.getProfile(postData);
  };

  self.getSearch = function (postData) {
    return ProfileFactory.getSearch(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('ProfileService', ProfileService);