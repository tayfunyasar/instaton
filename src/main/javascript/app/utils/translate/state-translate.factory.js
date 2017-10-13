StateTranslatorFactory.$inject = ['$state', '$translate'];

function StateTranslatorFactory($state, $translate) {

  var sevice = {
    getTranslatedState: getTranslatedState,
  };

  return sevice;

  function getTranslatedState(state) {
    var translatedTitle = $translate.instant(state.name + '.title');
    if (translatedTitle != (state.name + '.title')) {
      // Found a translation!
      state.title = translatedTitle;
    }

    // var translatedUrl = $translate.instant(state.name + '.url');
    // if (translatedUrl != (state.name + '.url')) { //Found a translation!
    //     state.url = translatedUrl;
    // }

    return state;
  }

}

angular.module('instaton.app.utils').factory('StateTranslatorFactory', StateTranslatorFactory);
