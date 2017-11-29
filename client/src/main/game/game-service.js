"use strict";

var angular = require("angular");
var _ = require("lodash");

angular.module("ticTacToe")
    .factory("gameService", gameService);

function gameService($stomp,$http, $q) {

    var service = {
        currentGame: undefined,
        startNewGame: startNewGame,
        endCurrentGame: endCurrentGame
    };
    return service;


    function startNewGame(gameParams) {
        return service.endCurrentGame()
            .then(function() {
            var response =[];
            var deferred=$q.defer();
             $stomp.connect('http://localhost:8080/games-websocket', {})
             .then(function (frame) {
             var subscription = $stomp.subscribe('/topic/game',
                                function (payload, headers, res) {
                                    service.currentGame = new Game(payload);
                                    deferred.resolve();
                            });
                   $stomp.send('/ticTacToe/create', gameParams);
                });
                return deferred.promise;

        });
    }


    function endCurrentGame() {
        if (!service.currentGame) {
            return $q.when();
        }
        return $http.delete("games/"+service.currentGame.id)
            .then(function() {
                service.currentGame = undefined;
            });
    }

    function Game(initialGameData) {
        var self = this;
        _.extend(self, initialGameData);
        var deferred=$q.defer();

        this.playTurn = playTurn;

        function playTurn(selectedMove) {

         selectedMove.id=self.id;
         $stomp.connect('http://localhost:8080/games-websocket', {})
                     .then(function (frame) {
                     var subscription = $stomp.subscribe('/topic/play',
                        function (payload, headers, res) {
                             _.extend(self, payload);
                             self.board = payload.board;
                             deferred.resolve(payload);
                                    });
                           $stomp.send('/ticTacToe/turn', selectedMove);
                        });
               return deferred.promise;

        }
    }}

