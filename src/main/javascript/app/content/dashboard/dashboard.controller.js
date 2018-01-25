DashboardController.$inject = ['ProfileService'];

function DashboardController(ProfileService) {
  var self = this;

  self.tagFilterList = [];

  self.$onInit = function () {

    ProfileService.getSearch().then(function (response) {
      self.searchResult = response;
    });
  };

  self.addTagFilter = function (tag) {
    self.tagFilterList.push(tag);
  };
}

angular.module('instaton.app.content').controller('DashboardController', DashboardController);