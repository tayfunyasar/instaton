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
          if (tweet.user.screenName == twitterUser.screenName) {
            self.searchResult.filteredTweets[index].user.gender = twitterUser.gender;
          }
          if (tweet.user.id == twitterUser.userId) {
            self.searchResult.filteredTweets[index].user.gender = twitterUser.gender;
          }
          if (tweet.user.id.toString().startsWith(twitterUser.userId.toString())) {
            self.searchResult.filteredTweets[index].user.gender = twitterUser.gender;
          }
          if (twitterUser.userId.toString().startsWith(tweet.user.id.toString())) {
            self.searchResult.filteredTweets[index].user.gender = twitterUser.gender;
          }
        });
      });

      self.searchResult.filteredTweets = _.uniq(self.searchResult.filteredTweets, function (tweet) {
        return tweet.user.screenName;
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
      screenName: tweet.user.screenName,
    };
    BlackUserIdEntityService.add(postData);
  };

  self.markUserAsMale = function (tweet) {
    tweet.user.gender = 'MALE';
    self.searchResult.filteredTweets = _.reject(self.searchResult.filteredTweets, function (filteredTweet) {
      return filteredTweet.user.screenName == tweet.user.screenName;
    });

    var postData = {
      gender: 'MALE',
      screenName: tweet.user.screenName,
    };
    TwitterUserService.add(postData);
  };

  self.markUserAsFemale = function (tweet) {
    tweet.user.gender = 'FEMALE';
    var postData = {
      gender: 'FEMALE',
      screenName: tweet.user.screenName,
    };
    TwitterUserService.add(postData);
  };

  self.markUserAsBot = function (tweet) {
    tweet.user.gender = 'BOT';
    self.searchResult.filteredTweets = _.reject(self.searchResult.filteredTweets, function (filteredTweet) {
      return filteredTweet.user.screenName == tweet.user.screenName;
    });

    var postData = {
      gender: 'BOT',
      screenName: tweet.user.screenName,
    };
    TwitterUserService.add(postData);
  };

  self.hideAll = function () {
    _.each(self.searchResult.filteredTweets, function (tweet) {
      if (tweet.user.gender != 'FEMALE') {
        self.hideUser(tweet);
      }
    });
  };
}

angular.module('instaton.app.content').controller('DashboardController', DashboardController);