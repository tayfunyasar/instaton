var _ = require('underscore');

DashboardInstagramController.$inject = ['InstagramUserService', '$window', '$timeout'];

function DashboardInstagramController(InstagramUserService, $window, $timeout) {
  var self = this;

  self.tagFilterList = [];

  self.$onInit = function () {
    InstagramUserService.list().then(function (response) {
      self.userList = response;
    });
  };

  var addInstagramUserService = function (postData) {
    self.userList = _.reject(self.userList, function (filteredTweet) {
      return filteredTweet.username == postData.username;
    });

    InstagramUserService.add(postData);
  };

  self.markUserAsFemale = function (twitterUser) {
    twitterUser.gender = 'FEMALE';
    var postData = {
      gender: twitterUser.gender,
      username: twitterUser.username,
    };
    addInstagramUserService(postData);
  };

  self.hideUser = function (twitterUser) {
    twitterUser.gender = 'BOT';
    var postData = {
      gender: twitterUser.gender,
      username: twitterUser.username,
    };
    addInstagramUserService(postData);
  };

  self.hideAll = function () {
    _.each(self.userList, function (user) {
      if (user.gender != 'FEMALE') {
        self.hideUser(user);
      }
    });
    $timeout(function () {
      $window.location.reload();
    }, 2000);
  };

}

angular.module('instaton.app.content').controller('DashboardInstagramController', DashboardInstagramController);