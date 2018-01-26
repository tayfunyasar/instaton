BlackUserIdEntityService.$inject = ['BlackUserIdEntityFactory'];

function BlackUserIdEntityService(BlackUserIdEntityFactory) {
  var self = this;

  self.add = function (postData) {
    return BlackUserIdEntityFactory.add(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('BlackUserIdEntityService', BlackUserIdEntityService);