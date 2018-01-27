var _ = require('underscore');

DashboardController.$inject = ['ProfileService', 'BlackHashTagEntityService', 'BlackUserIdEntityService', 'TwitterUserService'];

function DashboardController(ProfileService, BlackHashTagEntityService, BlackUserIdEntityService, TwitterUserService) {
  var self = this;

  self.tagFilterList = [];

  self.$onInit = function () {

    ProfileService.getSearch().then(function (response) {
      self.searchResult = response;

      return TwitterUserService.list();
    }).then(function (response) {
      self.twitterUserList = response;

      _.each(self.searchResult.filteredTweets, function (tweet, index) {
        _.each(self.twitterUserList, function (twitterUser) {
          if (tweet.user.id == twitterUser.userId) {
            self.searchResult.filteredTweets[index].user.gender = twitterUser.gender;
          }
        });
      });
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

  self.markUserAsMale = function (tweet) {
    tweet.user.gender = 'MALE';
    self.searchResult.filteredTweets = _.reject(self.searchResult.filteredTweets, function (filteredTweet) {
      return filteredTweet.user.id == tweet.user.id;
    });

    var postData = {
      gender: 'MALE',
      userId: tweet.user.id,
    };
    TwitterUserService.add(postData);
  };

  self.markUserAsFemale = function (tweet) {
    tweet.user.gender = 'FEMALE';
    var postData = {
      gender: 'FEMALE',
      userId: tweet.user.id,
    };
    TwitterUserService.add(postData);
  };

  self.markUserAsBot = function (tweet) {
    tweet.user.gender = 'BOT';
    self.searchResult.filteredTweets = _.reject(self.searchResult.filteredTweets, function (filteredTweet) {
      return filteredTweet.user.id == tweet.user.id;
    });

    var postData = {
      gender: 'BOT',
      userId: tweet.user.id,
    };
    TwitterUserService.add(postData);
  };
}

angular.module('instaton.app.content').controller('DashboardController', DashboardController);