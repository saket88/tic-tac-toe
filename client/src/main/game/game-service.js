"use strict";

var angular = require("angular");
var _ = require("lodash");

angular.module("ticTacToe")
    .factory("gameService", gameService);

function gameService(GAME_EVENTS, $rootScope, $stomp, $http, $q, $log) {
    var tabId;
    var service = {
        currentGame: undefined,
        startOrPlay: startOrPlay,
        endCurrentGame: endCurrentGame
    };
    return service;


    function startOrPlay(gameParams, selectedMove, boardSpinner) {

        tabId = selectedMove.tabId;
        var spinner = boardSpinner;
        var response = [];
        var deferred = $q.defer();
        $stomp.connect('http://localhost:8080/games-websocket', {})
            .then(function (frame) {
                var subscription = $stomp.subscribe('/topic/game',
                    function (payload, headers, res) {
                        if (!payload.turn && !payload.gameEnded) {
                            service.currentGame = new Game(payload);
                            deferred.resolve();
                        }
                        else if (payload.turn && !payload.gameEnded) {
                            service.currentGame = new Game(payload);
                            if (typeof (spinner) != "undefined" && typeof (tabId) != "undefined" && payload.tabId != tabId)
                                spinner.hide();
                            $rootScope.$broadcast(GAME_EVENTS.MOVE_COMPLETED, service.currentGame);
                            deferred.resolve(payload);
                        }
                        else if (payload.winner != null) {
                            service.currentGame = new Game(payload);
                            if (typeof (spinner) != "undefined")
                                spinner.hide();
                            $rootScope.$broadcast(GAME_EVENTS.MOVE_COMPLETED, service.currentGame);
                            $stomp.send('/ticTacToe/delete/' + service.currentGame.id, {});
                            $stomp.disconnect().then(function () {
                                $log.info('disconnected')
                            });
                            deferred.resolve(payload);
                        }
                        else {
                            $stomp.send('/ticTacToe/delete/' + service.currentGame.id, {});
                            $stomp.disconnect().then(function () {
                                $log.info('disconnected')
                            });
                            deferred.resolve();
                        }
                    });


                if (gameParams != '')
                    $stomp.send('/ticTacToe/create', gameParams);
                if (selectedMove != '') {
                    selectedMove.id = service.currentGame.id;

                    $stomp.send('/ticTacToe/turn', selectedMove);
                }
                if (gameParams == '' && selectedMove == '') {
                    $stomp.send('/ticTacToe/delete/' + service.currentGame.id, {});
                }

            });

        return deferred.promise;
    }


    function endCurrentGame() {
        var deferred = $q.defer();
        if (typeof service.currentGame == "undefined") {
            return $q.when();
        }
        //        $stomp.connect('http://localhost:8080/games-websocket', {})
        //                             .then(function (frame) {
        //                             var subscription = $stomp.subscribe('/topic/game',
        //                                function (payload, headers, res) {
        //                                service.currentGame = undefined;
        //                                $stomp.unsubscribe();
        //                                $stomp.disconnect().then(function () {
        //                                          $log.info('disconnected')
        //                                        });
        //                                deferred.resolve();
        //                                            });
        //                                   $stomp.send('/ticTacToe/delete/'+service.currentGame.id, {});
        //
        //                                });
        return deferred.promise;
    }

    function Game(initialGameData) {
        var self = this;
        _.extend(self, initialGameData);

    }
}

