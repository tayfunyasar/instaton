var _ = require('underscore');

DashboardTwitterController.$inject = ['ProfileService', 'BlackHashTagEntityService', 'TwitterUserService', 'BlackWordEntityService', '$window', '$timeout'];

function DashboardTwitterController(ProfileService, BlackHashTagEntityService, TwitterUserService, BlackWordEntityService, $window, $timeout) {
  var self = this;

  self.tagFilterList = [];

  self.$onInit = function () {
    TwitterUserService.list().then(function (response) {
      self.twitterUserList = response;
    });
  };

  // self.addTagFilter = function (tag) {
  //   self.tagFilterList.push(tag);
  //
  //   var postData = {
  //     keyword: tag,
  //   };
  //   BlackHashTagEntityService.add(postData);
  // };

  var addTwitterUserService = function (postData) {
    self.twitterUserList = _.reject(self.twitterUserList, function (filteredTweet) {
      return filteredTweet.screenName == postData.screenName;
    });

    TwitterUserService.add(postData);
  };

  self.hideUser = function (twitterUser) {
    twitterUser.gender = 'BOT';
    var postData = {
      gender: twitterUser.gender,
      screenName: twitterUser.screenName,
    };
    addTwitterUserService(postData);
  };

  self.markUserAsMale = function (twitterUser) {
    twitterUser.gender = 'MALE';
    var postData = {
      gender: twitterUser.gender,
      screenName: twitterUser.screenName,
    };
    addTwitterUserService(postData);
  };

  self.markUserAsFemale = function (twitterUser) {
    twitterUser.gender = 'FEMALE';
    var postData = {
      gender: twitterUser.gender,
      screenName: twitterUser.screenName,
    };
    addTwitterUserService(postData);
  };

  self.markUserAsBot = function (twitterUser) {
    twitterUser.gender = 'BOT';
    var postData = {
      gender: twitterUser.gender,
      screenName: twitterUser.screenName,
    };
    addTwitterUserService(postData);
  };

  self.hideAll = function () {
    _.each(self.twitterUserList, function (user) {
      if (user.gender != 'FEMALE') {
        self.hideUser(user);
      }
    });
    $timeout(function () {
      $window.location.reload();
    }, 2000);
  };

  self.deleteUser = function (user) {
    self.twitterUserList = _.reject(self.twitterUserList, function (filteredTweet) {
      return filteredTweet.screenName == user.screenName;
    });

    TwitterUserService.delete(user);
  };

  // self.addWordFilter = function (word) {
  //   self.twitterUserList = _.reject(self.twitterUserList, function (filteredTweet) {
  //     return filteredTweet.text.indexOf(word) > -1;
  //   });
  //
  //   var postData = {
  //     word: word,
  //   };
  //   BlackWordEntityService.add(postData);
  // };
}
angular.module('instaton.app.content').directive('imageonerror', function () {
  return {
    restrict: 'A',
    scope: {
      imageonerror: '&'
    },
    link: function (scope, element, attrs) {
      element.bind('load', function () {

      });
      element.bind('error', function () {
        scope.imageonerror();
      });
    }
  };
});
angular.module('instaton.app.content').controller('DashboardTwitterController', DashboardTwitterController);