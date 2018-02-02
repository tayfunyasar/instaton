BlackWordEntityFactory.$inject = ['$http'];

function BlackWordEntityFactory($http) {
  var self = this;

  self.add = function (postData) {
    return $http.post('/api/blackwordentity/add', postData).then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('BlackWordEntityFactory', BlackWordEntityFactory);