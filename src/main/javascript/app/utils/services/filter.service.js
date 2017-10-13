function FilterUtils() {

  return {
    isNotEmpty: function (filter) {
      return angular.defined(filter.value) && filter.value != null;
    }
  };
}

angular.module('instaton.app.utils').factory('FilterUtils', FilterUtils);
