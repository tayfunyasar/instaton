DashboardController.$inject = ['ProfileService'];

function DashboardController(ProfileService) {
  var self = this;

  self.$onInit = function () {

    ProfileService.getProfile().then(function (response) {
      self.profile = response.data;
    });
  };
}

angular.module('instaton.app.content').controller('DashboardController', DashboardController);