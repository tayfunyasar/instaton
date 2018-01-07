// Fills the sitemap according to app wide states, you can call sitemap from everywhere
angular.module('instaton.app').provider('sitemap', function () {
  var self = this;

  var pageList = [];
  var helperStateMapper = function (newstate, currentMap) {
    if (!angular.isArray(currentMap)) {
      return;
    }

    if (angular.isUndefined(newstate.parent)) {
      currentMap.push([
        newstate.name, {
          name: newstate.title,
          url: newstate.url,
        }
      ]);
    } else {
      angular.forEach(currentMap, function (storedItem) {
        if (!angular.isArray(storedItem)) {
          return;
        }

        // var storedState = $state.get(storedItem[0]);
        var storedState = storedItem[0];
        if (newstate.parent == storedState.name) {
          // New state is the child of an existing state
          storedState.isParent = true;
          // save newstate as a child of parent state
          if (storedState.children) {
            storedState.children.push(newstate);
          } else {
            storedState.children = [newstate];
          }

          storedItem.push([
            newstate.name, {
              name: newstate.title,
              url: newstate.url,
            }
          ]);
        } else {
          helperStateMapper(newstate, storedItem);
        }
      });
    }

    pageList = currentMap;
  };

  self.$get = ['$state', function ($state) {
    var map = [];
    var siteStates = $state.get(); // remove the redundant first angular state
    siteStates.splice(0, 1);
    angular.forEach(siteStates, function (state) {
      helperStateMapper(state, map);
    });

    return angular.toJson(pageList); // Returns the sitemap as JSON
  }];
});
