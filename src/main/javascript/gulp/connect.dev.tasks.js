var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var clean = require('gulp-clean');
var debowerify = require('debowerify');
var browserSync = require('browser-sync').create();
var connect = require('gulp-connect');
var modRewrite = require('connect-modrewrite');
//var beautify = require('gulp-jsbeautifier');
//
//gulp.task('beautify', function () {
//    return gulp.src(['./app/**/*.html', './app/**/*.js',  '!./app/translate/*'])
//        .pipe(
//      		beautify({
//              config: './app/.jsbeautifyrc',
//      		})
//        )
//        .pipe(gulp.dest('./app'));
//});

gulp.task('reload', ['build'], function() {
  console.log('Reloading');
  browserSync.reload();
});

gulp.task('watch', ['start-server'], function() {
  // watch for changes livereload browser
  gulp.watch([
    './app/**/*.*',
    './static/**/*.*',
    '!./app/instaton.template.js'
  ], ['reload']);

});

gulp.task('start-server', ['build'], function() { //
  var url = require('url');
  var proxy = require('proxy-middleware');

  var options = url.parse('http://localhost:8089/');
  options.route = '/';

  browserSync.init({
    server: {
      baseDir: '../webapp/',
      middleware: [proxy(options), modRewrite([
        '!\\.\\w+$ /index.html [L]'
      ])]
    },
    port: 5000,
    open: false
  });
});

gulp.task('clean', function() {
  gulp.src([
      '../webapp/assets/',
    ], {
      read: false
    })
    .pipe(clean({
      force: true
    }));
});

gulp.task('serve', ['start-server', 'watch']);