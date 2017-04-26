var gulp = require('gulp');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
var concat = require('gulp-concat');
var rev = require('gulp-rev');
var buffer = require('gulp-buffer');
var revReplace = require('gulp-rev-replace');
var gulpif = require('gulp-if');
var templatecache = require('gulp-angular-templatecache');
var htmlmin = require('gulp-htmlmin');
var sass = require('gulp-sass');

var config = {
    htmltemplates: ['./app/**/*.html'],
};

gulp.task('copy-views', function () {
    gulp.src('index.html')
      .pipe(gulp.dest('../webapp/'));

    gulp.src(config.htmltemplates)
        .pipe(gulp.dest('../webapp/assets/tpl/'));

    gulp.src('./app/**/*.jsp')
        .pipe(gulp.dest('../webapp/WEB-INF/jsp'));
});

gulp.task('htmlcache', function () {
    return gulp.src(config.htmltemplates)
        .pipe(
            htmlmin({
                collapseWhitespace: true
            })
        )
        .pipe(
            templatecache(
                'instaton.templates.js', {
                    templateHeader: 'angular.module("instaton.templates", []).run(["$templateCache", function($templateCache) {',
                    standAlone: false,
                    moduleSystem: 'Browserify',
                    transformUrl: function (url) {
                        return 'assets/tpl/' + url;
                    }
                })
        )
        .pipe(gulp.dest('./app'));

});

gulp.task('build-js', ['htmlcache'], function () {
    var b = browserify({
        entries: './app/main.js',
        debug: true
    });

    return b.bundle().on('error', function (err) {
            console.log(err);
            this.emit('end');
        })
        .pipe(source('instaton.js'))

        .pipe(gulp.dest('../webapp/assets/js'));

});

gulp.task('build-styles', function () {
    gulp.src('./static/sass/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('../webapp/assets/css'));

    gulp.src('./static/styles/*.css')
        .pipe(concat('styles.all.css'))
        .pipe(gulp.dest('../webapp/assets/css'));
});

gulp.task('copy-static', function () {

    gulp.src('./static/fonts/**')
        .pipe(gulp.dest('../webapp/assets/fonts/'));

    gulp.src('./static/pop-templates/**')
        .pipe(gulp.dest('../webapp/assets/pop-templates/'));

    gulp.src('./static/img/**')
        .pipe(gulp.dest('../webapp/assets/img/'));

    gulp.src('./static/translate/**')
        .pipe(gulp.dest('../webapp/assets/translate/'));
});

gulp.task('revfiles', ['copy-views', 'build-styles', 'build-js'], function () {
    return gulp
        .src([
            '../webapp/assets/css/instaton.css',
            '../webapp/assets/css/styles.all.css',
            '../webapp/assets/js/instaton.js'
        ])
        .pipe(rev())
        .pipe(gulp.dest('../webapp/rev')) // write rev'd assets to build dir
        .pipe(rev.manifest())
        .pipe(gulp.dest('../webapp/rev'));
});

gulp.task('copyrevfiles', ['revfiles'], function () {
    gulp.src('../webapp/rev/*.css')
        .pipe(gulp.dest('../webapp/assets/css'));
    gulp.src('../webapp/rev/*.js')
        .pipe(gulp.dest('../webapp/assets/js'));
    return;
});

gulp.task('revreplace', ['copyrevfiles', 'revfiles', 'copy-views'], function () {
    var manifesto = gulp.src('../webapp/rev/rev-manifest.json');

    gulp.src('../webapp/index.html')
        .pipe(
            revReplace({
                manifest: manifesto
            })
        )
        .pipe(gulp.dest('../webapp/'));

});
gulp.task('build', ['build-js', 'build-styles', 'copy-static', 'copy-views']);
gulp.task('assemble', ['build', 'revfiles', 'revreplace']);
