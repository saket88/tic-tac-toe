"use strict";

var angular = require("angular");
var _ = require("lodash");

angular.module("ticTacToe")
    .controller("GameController", GameController)

function GameController(GAME_EVENTS, PIECES, gameService, $scope, $q, $mdToast, $mdMedia, $timeout) {
  var vm = this;
    var deferredMove;
    var boardSpinner;

    vm.init = init;
    vm.startGame = startGame;
    vm.endGame = endGame;
    init();


    function init() {
        vm.gameExists = false;
        vm.screenIsSmall = screenIsSmall();
        resetStats();

        return $q.all({

            PIECES: PIECES
    })
    }

    function resetStats() {
            vm.gameStats = {
                currentRound: 0,
                ties: 0,
                wins: {}
            };
            vm.gameStats.wins[PIECES.X] = 0;
            vm.gameStats.wins[PIECES.O] = 0;

            return $q.when();
        }

        function startGame() {

           return $q.when();
         }

         function endGame() {

             return $q.when();
         }




    function screenIsSmall() {
        return !$mdMedia("gt-xs");
    }

    function handleSmallScreen(screenIsSmall) {
        vm.screenIsSmall = screenIsSmall;
    }


}


