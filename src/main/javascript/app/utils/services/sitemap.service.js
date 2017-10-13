SitemapService.$inject = ['sitemap', '$state'];

function SitemapService(sitemap, $state) {
  var self = this;
  //  console.log(sitemap);

  // Returns the states list with particular key-value pair
  self.stateCategorizer = function (key, value) {
    var list = [];

    angular.forEach($state.get(), function (state) {
      var keys = Object.keys(state);
      if (keys.indexOf('sitemap') > -1 && state.sitemap[key] == value) {
        list.push(state);
      }
    });

    return list;
  };

  self.isStateParent = function (stateName) {
    return $state.get(stateName).isParent;
  };

  self.getChildren = function (stateName) {
    return $state.get(stateName).children;
  };

  /*
      self.pageList = SitemapFactory.pageList;

      self.filterByState = function(state) {
          self.filterByState(state, false);
      };

      self.doFilterPageListByState = function(state) {
          var customPageList = [];
          _.each(self.pageList, function(page) {
              if (page.state == state) {
                  customPageList = page.subPageList;
              }
          });
          return customPageList;
      };

      self.doFilterGivenPageListByState = function(givenList, state) {
          var filteredPageList = [];
          _.each(givenList, function(page) {
              if (page.state == state) {
                  filteredPageList = page.subPageList;
              }
          });
          return filteredPageList;
      };
  */
}

angular.module('instaton.app.utils').service('SitemapService', SitemapService);
