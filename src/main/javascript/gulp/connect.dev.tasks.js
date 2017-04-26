var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var clean = require('gulp-clean');
var debowerify = require('debowerify');
var browserSync = require('browser-sync').create();
var connect = require('gulp-connect');
var modRewrite = require('connect-modrewrite');
var beautify = require('gulp-jsbeautifier');

gulp.task('beautify', function () {
    return gulp.src(['./app/**/*.html', './app/**/*.js',  '!./app/translate/*'])
        .pipe(
    		beautify({
    			config: './app/.jsbeautifyrc'
    		})
        )
        .pipe(gulp.dest('./app'));
});

gulp.task('reload', ['build'], function() {
    browserSync.reload();
});

gulp.task('watch', ['build', 'beautify'], function() {
    // watch for changes livereload browser
    gulp.watch([
        '**/*.html',
        'app/**/*.*',
        'static/**/*.*',
        '!app/instaton.templates.js'
    ], ['reload']);

});

gulp.task('start-server', ['watch'], function() {
    var url = require('url');
    var proxy = require('proxy-middleware');

    var options = url.parse('http://localhost:8081/giris');
    options.route = '/giris';

    var optionsForPortal = url.parse('http://localhost:8080/');
    optionsForPortal.route = '/bonus';

    browserSync.init({
        server: {
            baseDir: "../webapp/",
            middleware: [proxy(options), proxy(optionsForPortal), modRewrite([
                '!\\.\\w+$ /index.html [L]'
            ])]
        },
        port: 3000,
        open: false
    });
});

gulp.task('clean', function() {
	console.log('clean');
    return gulp.src(['app/tmp', 'dist/'], {
            read: false
        })
        .pipe(clean());
});

gulp.task('serve', ['start-server', 'watch']);
