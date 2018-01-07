var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var concat = require('gulp-concat');
var replace = require('gulp-replace');
var rev = require('gulp-rev');
var revReplace = require('gulp-rev-replace');
var gulpif = require('gulp-if');
var eslint = require('gulp-eslint');
var htmlhint = require('gulp-htmlhint');
var sass = require('gulp-sass');
var templateCache = require('gulp-angular-templatecache');
var htmlmin = require('gulp-htmlmin');
var pump = require('pump');
var uglify = require('gulp-uglify');

gulp.task('htmlCache', ['htmlhint', 'lint'], function() {
  return gulp.src('./app/**/*.html')
    .pipe(htmlmin({
      collapseWhitespace: true
    }))
    .pipe(templateCache(
      'instaton.template.js', {
        templateHeader: 'angular.module("instaton.app.template", []).run(["$templateCache", function($templateCache) {',
        standAlone: false,
        moduleSystem: 'Browserify',
        transformUrl: function(url) {
          return url;
        }
      }
    )).pipe(gulp.dest('./app'));
});

gulp.task('lint', function() {
  // ESLint ignores files with 'node_modules' paths.
  // So, it's best to have gulp ignore the directory as well.
  // Also, Be sure to return the stream from the task;
  // Otherwise, the task may end before the stream has finished.
  return gulp.src([
      './app/**/*.js',
      '!./app/content/reports/components/report-filter/daterange-picker/**/*.js',
      '!./app/content/reports/components/report-filter/date-picker/**/*.js',
      '!./app/content/reports/components/report-filter/date-period-filter/*.js',

      '!./app/instaton.template.js',
      '!./app/content/reports/components/report-filter/report-excel-download/*',
      '!./app/content/components/date-picker/templates/*'
    ])
    // eslint() attaches the lint output to the 'eslint' property
    // of the file object so it can be used by other modules.
    .pipe(
      eslint({
        'quiet': true
      })
    )
    // eslint.format() outputs the lint results to the console.
    // Alternatively use eslint.formatEach() (see Docs).
    .pipe(eslint.format())
    // To have the process exit with an error code (1) on
    // lint error, return the stream and pipe to failAfterError last.
    .pipe(eslint.failAfterError());
});

gulp.task('htmlhint', function() {
  return gulp.src(
      [
        './app/**/*.html',
        '!./app/content/reports/components/report-filter/daterange-picker/**/*.html',
        '!./app/content/reports/components/report-filter/date-picker/**/*.html',
        '!./app/content/reports/components/report-filter/date-period-filter/*.html',
      ])
    .pipe(
      htmlhint('./app/.htmlhintrc')
    )
    .pipe(htmlhint.failReporter());
});

gulp.task('copy-views', ['lint'], function() {
  gulp.src('./app/index.html')
    .pipe(replace(/LoyaltyBuildTime/g, 'LoyaltyBuildTime: ' + (new Date()).toLocaleString()))
    .pipe(gulp.dest('../webapp/'));

  gulp.src('./app/**/*.html')
    .pipe(gulp.dest('../webapp/assets/tpl/'));

});

gulp.task('build-js', ['htmlCache', 'lint'], function() {

  var b = browserify({
    entries: './app/main.js',
    debug: true
  });

  return b.bundle().on('error', function(err) {
      console.log(err);
      this.emit('end');
    })
    .pipe(source('instaton.js'))

    .pipe(gulp.dest('../webapp/assets/js'));

});

gulp.task('build-styles', function() {

  gulp.src('./static/sass/**/*.scss')
    .pipe(sass().on('error', sass.logError))
    .pipe(gulp.dest('../webapp/assets/css'));

  gulp.src('./static/styles/*.css')
    .pipe(concat('styles.all.css'))
    .pipe(gulp.dest('../webapp/assets/css'));
});

gulp.task('copy-static', function() {

  gulp.src('./static/fonts/**')
    .pipe(gulp.dest('../webapp/assets/fonts/'));

  gulp.src('./static/img/**')
    .pipe(gulp.dest('../webapp/assets/img/'));

  gulp.src('./app/**/*.jsp')
    .pipe(gulp.dest('../webapp/WEB-INF/jsp'));
});

gulp.task('compress', ['copy-views', 'build-styles', 'build-js'], function(cb) {
  pump([
      gulp.src('../webapp/assets/js/instaton.js'),
      uglify({
        mangle: false
      }),
      gulp.dest('../webapp/assets/min')
    ],
    cb
  );
});

gulp.task('revfiles', ['compress'], function() {
  return gulp
    .src([
      '../webapp/assets/css/instaton.css',
      // '../webapp/assets/min/instaton.js',
      '../webapp/assets/img/favicon.ico',
    ])
    .pipe(rev())
    .pipe(gulp.dest('../webapp/rev')) // write rev'd assets to build dir
    .pipe(rev.manifest())
    .pipe(gulp.dest('../webapp/rev'));
});

gulp.task('copyrevfiles', ['revfiles'], function() {
  gulp.src('../webapp/rev/*.css')
    .pipe(gulp.dest('../webapp/assets/css'));
  gulp.src('../webapp/assets/min/*.js')
    .pipe(gulp.dest('../webapp/assets/js'));
  gulp.src('../webapp/rev/*.ico')
    .pipe(gulp.dest('../webapp/assets/img'));
});

gulp.task('revreplace', ['copyrevfiles', 'revfiles', 'copy-views'], function() {
  var manifesto = gulp.src('../webapp/rev/rev-manifest.json');

  gulp.src('../webapp/index.html')
    .pipe(
      revReplace({
        manifest: manifesto
      })
    )
    .pipe(gulp.dest('../webapp/'));
});

gulp.task('build', ['build-js', 'copy-views', 'build-styles', 'copy-static']); //,
gulp.task('assemble', ['build', 'revfiles', 'revreplace']);