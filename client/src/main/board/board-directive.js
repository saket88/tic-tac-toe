"use strict";

var angular = require("angular");
var _ = require("lodash");
var visibleSize = require("../utils/visible-size");

angular.module("ticTacToe")
    .directive("board", board);



var GRID_COLOR = "#eee";
var PIECE_COLOR = "#000";


function board(GAME_EVENTS, PIECES, $window, $timeout, $log) {
    return {
        restrict: "E",
        template: '<canvas style="margin: auto; display: block"></canvas>',
        scope: {
            numRows: "=",
            numCols: "="
        },
        link: link
    };

    function link($scope, iElem) {

        var canvas = iElem.find("canvas")[0];
        var ctx = canvas.getContext("2d");

        var numRows, numCols;

        var cellSize; // square cell width = height
        var GRID_LINE_WIDTH = 1;

        var canvasWidth, canvasHeight;
        var windowInnerWidth;

        var board;
        var lastTurnResult;


        $scope.$watch("numRows", function(value) {
            numRows = value;
            resetBoard();
            resizeAndDrawCanvas(true);
        });
        $scope.$watch("numCols", function(value) {
            numCols = value;
            resetBoard();
            resizeAndDrawCanvas(true);
        });

        $window.addEventListener("resize", _.debounce(function() {
            resizeAndDrawCanvas(false);
        }, 150));


        //---------------------------------------------------//


        function resetBoard() {
            board = undefined;
            lastTurnResult = undefined;
            if (numRows > 0 && numCols > 0) {
                board = [];
                for (var i = 0; i < numRows; i++) {
                    board[i] = [];
                    for (var j = 0; j < numCols; j++) {
                        board[i][j] = null;
                    }
                }
            }
        }


        function resizeAndDrawCanvas(force) {
            if (!force && windowInnerWidth === window.innerWidth) {
                return;
            }

            if (numRows && numCols) {
                resizeCanvas();
                drawGameBoard();
                windowInnerWidth = window.innerWidth;
            }

        }


        function resizeCanvas() {
            // We assume that the parent container can adapt to content vertically but not horizontally.

            // 1st pass: resize to available width
            cellSize = _.floor(canvas.parentNode.clientWidth/numCols); // square cells
            canvasWidth = numCols*cellSize;
            canvasHeight = numRows*cellSize;

            // also clears canvas
            canvas.width = canvasWidth;
            canvas.height = canvasHeight;

            // 2nd pass: resize to visible height
            var visibleHeight = visibleSize(canvas).height;

            cellSize = _.floor(Math.min(canvas.clientWidth/numCols, visibleHeight/numRows));
            canvasWidth = numCols*cellSize;
            canvasHeight = numRows*cellSize;

            // also clears canvas
            canvas.width = canvasWidth;
            canvas.height = canvasHeight;
        }

        function drawGameBoard() {
            ctx.strokeStyle = GRID_COLOR;
            ctx.lineWidth = GRID_LINE_WIDTH;

            // Draw horizontal lines
            for (var i = 0; i < numRows - 1; i++) {
                ctx.beginPath();
                ctx.moveTo(0, cellSize*(i + 1));
                ctx.lineTo(canvas.width, cellSize*(i + 1));
                ctx.stroke();
            }

            // Draw vertical lines
            for (var j = 0; j < numCols - 1; j++) {
                ctx.beginPath();
                ctx.moveTo(cellSize*(j + 1), 0);
                ctx.lineTo(cellSize*(j + 1), canvas.height);
                ctx.stroke();
            }

        }

    }
}









