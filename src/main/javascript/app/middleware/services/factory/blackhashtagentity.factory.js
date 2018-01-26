BlackHashTagEntityFactory.$inject = ['$http'];

function BlackHashTagEntityFactory($http) {
  var self = this;

  self.add = function (postData) {
    return $http.post('/api/blackhashtagentity/add', postData).then(function (response) {
      return response.data;
    });
  };

  return self;
}

angular.module('instaton.app.middleware').factory('BlackHashTagEntityFactory', BlackHashTagEntityFactory);