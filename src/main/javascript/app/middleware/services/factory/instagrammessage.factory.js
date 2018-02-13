InstagramMessageFactory.$inject = ['$http'];

function InstagramMessageFactory($http) {
  var self = this;

  self.list = function () {
    return $http.post('/instagram/messages').then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('InstagramMessageFactory', InstagramMessageFactory);