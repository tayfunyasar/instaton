angular.module('instaton.app.content').filter('filterByTags', function () {
  return function (tweets, tags) {
    // (items || []).forEach(function (item) { // Check each item
    //   var matches = tags.some(function (tag) { // If there is some tag
    //     (item.entities.hashTags || []).forEach(function (hashTag) {
    //       return (hashTag.text.indexOf(tag) == -1);
    //     });
    //   }); // we have a match
    //   if (!matches) { // If it matches
    //     filtered.push(item); // put it into the `filtered` array
    //   }
    // });
    var filteredArray = [];
    (tweets || []).forEach(function (tweet) {
      var contains = false;
      tweet.entities.hashTags.forEach(function (hashTag) {
        tags.forEach(function (tag) {
          if (tag == hashTag.text) {
            contains = true;
          }
        });
      });
      if (!contains) {
        filteredArray.push(tweet);
      }
    });

    return filteredArray; // Return the array with items that match any tag
  };
});