"use strict";

var angular = require("angular");
var _ = require("lodash");

angular.module("ticTacToe")
    .factory("gameService", gameService);

function gameService(GAME_EVENTS,$rootScope,$stomp,$http, $q) {

    var service = {
        currentGame: undefined,
        startOrPlay: startOrPlay,
        endCurrentGame: endCurrentGame
    };
    return service;


    function startOrPlay(gameParams,selectedMove) {
        var response =[];
            var deferred=$q.defer();
             $stomp.connect('http://localhost:8080/games-websocket', {})
             .then(function (frame) {
             var subscription = $stomp.subscribe('/topic/game',
                                function (payload, headers, res) {
                                   if(!payload.turn && !payload.gameEnded){
                                    service.currentGame = new Game(payload);
                                    deferred.resolve();
                                    }
                                     else if(payload.turn && !payload.gameEnded){
                                                                         service.currentGame = new Game(payload);
                                                                         $rootScope.$broadcast(GAME_EVENTS.MOVE_COMPLETED, service.currentGame);
                                                                         deferred.resolve(payload);
                                                                         }
                                                                         else{
                                                                         service.currentGame = undefined;
                                                                         $stomp.disconnect().then(function () {
                                                                         $log.info('disconnected')
                                                                          });
                                                                         deferred.resolve();
                                                                         }});

                                if(gameParams!='')
                                                   $stomp.send('/ticTacToe/create', gameParams);
                                                   if(selectedMove!=''){
                                                    selectedMove.id=service.currentGame.id;
                                                    $stomp.send('/ticTacToe/turn',selectedMove);
                                                    }
                                                    if(gameParams=='' && selectedMove==''){
                                                    $stomp.send('/ticTacToe/delete/'+service.currentGame.id, {});
                                                    }
                    });

                    return deferred.promise;
                 }


    function endCurrentGame() {
    var deferred=$q.defer();
        if (typeof service.currentGame =="undefined") {
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

