BlackWordEntityService.$inject = ['BlackWordEntityFactory'];

function BlackWordEntityService(BlackWordEntityFactory) {
  var self = this;

  self.add = function (postData) {
    return BlackWordEntityFactory.add(postData);
  };

  return self;
}

angular.module('instaton.app.middleware').service('BlackWordEntityService', BlackWordEntityService);