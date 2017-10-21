"use strict";

var angular = require("angular");
require("angular-material");

var PIECES = {
    X: "X",
    O: "O"
};

var GAME_EVENTS = {
    GAME_STARTED: "game started",
    MOVE_COMPLETED: "move completed",
    MOVE_SELECTED: "move selected",
    SHOW_LAST_MOVE: "show last move"
};


angular
    .module("ticTacToe", ["ngMaterial"])
    .constant("GAME_EVENTS", GAME_EVENTS)
    .constant("PIECES", PIECES)
    .config(configureIcons)
    .config(configureThemes);

require("./board");
require("./game");
require("./utils");


function configureIcons($mdIconProvider) {
    $mdIconProvider
        .icon("play", "resources/material-design-icons/ic_play_arrow_black_24px.svg")
        .icon("stop", "resources/material-design-icons/ic_stop_black_24px.svg")

    ;
}

function configureThemes($mdThemingProvider) {
    $mdThemingProvider.theme("error");
}
