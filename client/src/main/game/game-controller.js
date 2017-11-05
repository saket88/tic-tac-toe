"use strict";

var angular = require("angular");
var _ = require("lodash");

angular.module("ticTacToe")
    .controller("GameController", GameController)

function GameController(GAME_EVENTS, PIECES, gameService, $scope, $q, $mdToast, $mdMedia, $timeout) {
    var vm = this;
    var deferredMove;

    vm.init = init;
    vm.startGame = startGame;
    vm.endGame = endGame;
    $scope.$on(GAME_EVENTS.MOVE_SELECTED, selectPlayerMove);
    $scope.$watch(screenIsSmall, handleSmallScreen);
    init();
    function init() {
        vm.gameExists = false;
        vm.screenIsSmall = screenIsSmall();
        resetStats();

        return $q.all({

            PIECES: PIECES,
              gameConfig: {
                            firstPlayer: "X"
              }
    }).then(function(data) {
                  _.extend(vm, data);
              });

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

           return resetStats()
                       .then(initRound)
                       .then(play);
         }

         function endGame() {
              vm.gameExists = false;
              vm.paused = false;
              vm.currentGame = undefined;
              deferredMove = undefined;
              if (gameService.currentGame) {
                       return gameService.endCurrentGame();
                     } else {
                         return $q.when();
               }
         }


     function initRound() {
       var gameConfig = _.cloneDeep(vm.gameConfig);
       return gameService.startNewGame(gameConfig)
            .then(function() {
                vm.currentGame = gameService.currentGame;
                vm.gameExists = true;
                vm.paused = false;
                vm.gameStats.currentRound++;
                $scope.$broadcast(GAME_EVENTS.GAME_STARTED, gameService.currentGame);
            })
            .catch(handleError);
    }

    function updateStats(result) {
            if (result.gameEnded) {
                if (result.winner) {
                    vm.gameStats.wins[result.winner]++;
                } else {
                    vm.gameStats.ties++;
                }
            }
            return $q.when();
        }

    function setPaused(isPaused) {
            vm.paused = isPaused;
            if (!isPaused) {
                if (vm.pausedResult) {
                    $scope.$broadcast(GAME_EVENTS.MOVE_COMPLETED, vm.pausedResult);
                    vm.pausedResult = undefined;
                }
                play();
            }
        }


   function play() {
            deferredMove = $q.defer();
            deferredMove.promise
            .then(function(selectedCell) {
                return gameService.currentGame.playTurn(selectedCell);
            })
            .then(function(result) {
                if (vm.gameExists && !vm.paused) {
                    $scope.$broadcast(GAME_EVENTS.MOVE_COMPLETED, result);
                } else if (vm.gameExists && vm.paused) {
                    vm.pausedResult = result;
                }
                return result;
            })
            .then(function(result) {
                if (!vm.gameExists) {
                    return;
                } else if (!result.gameEnded && !vm.paused) {
                    play();
                } else if (result.gameEnded) {
                    updateStats(result);
                    gameService.endCurrentGame();
                        endGame();
                }
            })
            .catch(function(response) {
                if (vm.gameExists) {
                    return handleError(response);
                } else {
                    return $q.reject(response);
                }
            });
    }


    function screenIsSmall() {
        return !$mdMedia("gt-xs");
    }

    function handleSmallScreen(screenIsSmall) {
        vm.screenIsSmall = screenIsSmall;
    }

    function handleError(response) {
        var message;
        if (response.status === 404) {
            message = "Game no is longer active. Games are automatically deleted after 15 minutes of inactivity.";
        } else {
            message = "Oops. The game ended to an error. Sorry.";
        }

        return $q.reject(response);
    }

     function selectPlayerMove(event, selectedCell) {
            if (!vm.gameExists) {
                return;
            }



            deferredMove.resolve(selectedCell);
        }


}


