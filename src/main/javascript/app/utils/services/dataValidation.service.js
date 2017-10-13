DataValidationService.$inject = [];

function DataValidationService() {
  var service = {
    isEmpty: isEmpty,
    convertFilterDate: convertFilterDate,
  };

  return service;

  function isEmpty(obj) {
    return Object.keys(obj).length === 0;
  }

  function convertFilterDate(data) {
    data.startDate = data.dateRange.startDate;
    data.endDate = data.dateRange.endDate;
    delete data.dateRange;
  }
}

angular.module('instaton.app.utils').service('DataValidationService', DataValidationService);
