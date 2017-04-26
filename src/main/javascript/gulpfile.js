var gulp = require('gulp');

require('./gulp/build.dev.tasks');
require('./gulp/connect.dev.tasks');

gulp.task('default', ['serve']);