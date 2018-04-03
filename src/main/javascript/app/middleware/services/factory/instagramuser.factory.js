InstagramUserFactory.$inject = ['$http'];

function InstagramUserFactory($http) {
  var self = this;

  self.add = function (postData) {
    return $http.post('/api/instagramuser/add', postData).then(function (response) {
      return response.data;
    });
  };

  self.delete = function (postData) {
    return $http.post('/api/instagramuser/delete', postData).then(function (response) {
      return response.data;
    });
  };

  self.list = function () {
    return $http.post('/api/instagramuser/list').then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('InstagramUserFactory', InstagramUserFactory);