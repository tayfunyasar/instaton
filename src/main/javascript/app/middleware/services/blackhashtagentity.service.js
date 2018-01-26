BlackHashTagEntityService.$inject = ['BlackHashTagEntityFactory'];

function BlackHashTagEntityService(BlackHashTagEntityFactory) {
  var self = this;

  self.add = function (postData) {
    return BlackHashTagEntityFactory.add(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('BlackHashTagEntityService', BlackHashTagEntityService);