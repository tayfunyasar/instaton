ProfileFactory.$inject = ['$http'];

function ProfileFactory($http) {
  var self = this;

  self.getProfile = function (postData) {
    return $http.post('/api/profile/current').then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('ProfileFactory', ProfileFactory);