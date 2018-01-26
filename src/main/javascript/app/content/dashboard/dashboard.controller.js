DashboardController.$inject = ['ProfileService', 'BlackHashTagEntityService'];

function DashboardController(ProfileService, BlackHashTagEntityService) {
  var self = this;

  self.tagFilterList = [];

  self.$onInit = function () {

    ProfileService.getSearch().then(function (response) {
      self.searchResult = response;
    });

  };

  self.addTagFilter = function (tag) {
    self.tagFilterList.push(tag);

    var postData = {
      keyword: tag,
    };
    BlackHashTagEntityService.add(postData);
  };
}

angular.module('instaton.app.content').controller('DashboardController', DashboardController);