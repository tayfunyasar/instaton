TwitterUserFactory.$inject = ['$http'];

function TwitterUserFactory($http) {
  var self = this;

  self.add = function (postData) {
    return $http.post('/api/twitteruser/add', postData).then(function (response) {
      return response.data;
    });
  };

  self.delete = function (postData) {
    return $http.post('/api/twitteruser/delete', postData).then(function (response) {
      return response.data;
    });
  };

  self.list = function () {
    return $http.post('/api/twitteruser/list').then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('TwitterUserFactory', TwitterUserFactory);