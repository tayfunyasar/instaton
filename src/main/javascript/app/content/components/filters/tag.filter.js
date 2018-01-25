angular.module('instaton.app.content').filter('filterByTags', function () {
  return function (items, tags) {
    var filtered = []; // Put here only items that match
    (items || []).forEach(function (item) { // Check each item
      var matches = tags.some(function (tag) { // If there is some tag
        (item.entities.hashTags || []).forEach(function (hashTag) {
          return (hashTag.text.indexOf(tag) == -1);
        });
      }); // we have a match
      if (!matches) { // If it matches
        filtered.push(item); // put it into the `filtered` array
      }
    });
    return filtered; // Return the array with items that match any tag
  };
});