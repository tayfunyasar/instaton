TweetListController.$inject = [];

function TweetListController() {
  var self = this;

  self.aa = '';
};

angular.module('instaton.app.content.components', []).component('tweetList', {
  controller: TweetListController,
  controllerAs: 'self',
  templateUrl: './tpl.html',
  bindings: {
    data: '=',
  },
});
