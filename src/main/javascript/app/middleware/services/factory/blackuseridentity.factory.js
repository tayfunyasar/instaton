BlackUserIdEntityFactory.$inject = ['$http'];

function BlackUserIdEntityFactory($http) {
  var self = this;

  self.add = function (postData) {
    return $http.post('/api/blackuseridentity/add', postData).then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('BlackUserIdEntityFactory', BlackUserIdEntityFactory);