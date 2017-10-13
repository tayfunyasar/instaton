$ = jQuery = require('jquery/dist/jquery');
moment = require('moment');

require('angular');
require('bootstrap/dist/js/bootstrap');
require('./instaton');
require('./content');
require('./footer');
require('./header');
require('./utils');

angular.bootstrap(document, ['instaton']);
