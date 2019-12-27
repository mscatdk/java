// /////////////////////////////////////////////////////////////////
// Required
// /////////////////////////////////////////////////////////////////
var gulp = require('gulp'),
    uglify = require('gulp-uglify'),
    concat = require('gulp-concat'),
    del = require('del'),
    rename = require('gulp-rename');

// /////////////////////////////////////////////////////////////////
// Build
// /////////////////////////////////////////////////////////////////
var BUILD_FOLDER = 'gulp/build';
var DEPLOY_FOLDER = 'src/main/webapp';
var VENDOR_LIB = 'gulp/vendor/';

var vendorLibs = [

];

// /////////////////////////////////////////////////////////////////
// Build
// /////////////////////////////////////////////////////////////////
gulp.task('build:clean', function() {
  return del(['gulp/dist/']);
});

gulp.task('build:scripts', function() {
  return gulp.src(['gulp/js/**/*.js'])
  .pipe(concat('app.js'))
  .pipe(rename({suffix:'.min'}))
  .pipe(uglify({mangle: false}))  // "{mangle: false}" is need as angular will fail if not included.
  .pipe(gulp.dest('gulp/dist/js'));
});

gulp.task('build:vendor', function() {

var target = gulp.src(['gulp/view/index.jsp']);

var source = gulp.src(['gulp/dist/js/**/*.js'], {read: false});

return target
        .pipe(inject(gulp.src(bowerFiles(), {read: false}), {name: 'bower'}))
        .pipe(inject(source))
        .pipe(gulp.dest('gulp/dist/'), {base: "gulp/dist"});
});

gulp.task("build", gulp.series("build:clean", "build:scripts"));

// /////////////////////////////////////////////////////////////////
// Deploy
// /////////////////////////////////////////////////////////////////
gulp.task('deploy:clean', function() {
  return del('src/main/webapp/js/app.min.js');
});

gulp.task('deploy:copy', function() {
  return gulp.src('gulp/dist/js/app.min.js')
  .pipe(gulp.dest('src/main/webapp/js/'));
});

gulp.task("deploy", gulp.series("deploy:clean", "deploy:copy"));
// /////////////////////////////////////////////////////////////////
// Default Task
// /////////////////////////////////////////////////////////////////
gulp.task('default', gulp.series('build', 'deploy'));
