var _ = require('underscore');

DashboardController.$inject = ['ProfileService', 'BlackHashTagEntityService', 'BlackUserIdEntityService'];

function DashboardController(ProfileService, BlackHashTagEntityService, BlackUserIdEntityService) {
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

  self.hideUser = function (tweet) {
    self.searchResult.filteredTweets = _.reject(self.searchResult.filteredTweets, function (filteredTweet) {
      return filteredTweet == tweet;
    });

    var postData = {
      userId: tweet.user.id,
    };
    BlackUserIdEntityService.add(postData);
  };
}

angular.module('instaton.app.content').controller('DashboardController', DashboardController);