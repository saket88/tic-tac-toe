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

        this.playTurn = playTurn;

        function playTurn(selectedMove) {
            return $http.post("games/"+self.id+"/turn", selectedMove)
            .then(function(response) {
                _.extend(self, response.data);
                self.board = response.data.board;
                return response.data;
            });
        }
    }}

