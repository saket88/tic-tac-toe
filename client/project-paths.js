"use strict";

var paths = {
    appSourceMain: 'src/main/tic-tac-toe.js',
    scripts: ['node_modules/sockjs-client/dist/sockjs.min.js'],
    appSourceAll: 'src/main/**/*.js',
    html: ['src/main/**/*.html', '!src/main/**/*.spec.html'],
    resources: ['src/main/resources/**'],
    libResources: ['node_modules/angular-material/angular-material.css'],
    externalJS:['node_modules/sockjs-client/dist/sockjs.min.js','node_modules/stompjs/lib/stomp.min.js'],
    build: '../public',
    appDestName: 'tic-tac-toe.js',
    libDestName: 'libs.js'
};

module.exports = paths;
