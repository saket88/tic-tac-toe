package com.ws.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

import static java.util.stream.IntStream.range;


@AllArgsConstructor
@Builder
public class Game {


    public Game(){

    }

    @Getter
    private final String id = UUID.randomUUID().toString();

    @Getter
    GameSign[][] board;

    @Getter
    private Player nextPlayer;


    @Getter
    private GameSign winner;

    @Getter
    private boolean tie;

    @Getter
    private Cell startWinCoordinates;

    @Getter
    private Cell endWinCoordinates;

    @Getter
    private Cell move;



    public void playTurn(Cell cell) {
        int row=cell.getRow();
        int column = cell.getColumn();
        GameSign[][] gameSigns = getBoard();
        GameSign currentGameSign = getNextPlayer().getGameSign();
        gameSigns[row][column] = currentGameSign;
        move=new Cell(row,column);
        nextPlayer = new Player(nextPlayer.getGameSign().toggle().name());
        determineResult(currentGameSign);

    }


    private void determineResult(GameSign currentGameSign) {
        if (isWinner()) {
            winner = currentGameSign;
        }
        else if (!hasBlank()) {
            tie = true;
        }
    }

    private boolean isWinner() {

        final boolean[] hasWinner = new boolean[1];
        int rows = board.length;
        int cols = board[0].length;

        range(0, rows * cols)
                .forEach((int n) -> {
                    int i = n / cols;
                    int j = n % cols;
                    row(hasWinner, cols, i ,j);
                    column(hasWinner, rows, i,j);
                    leftDiagonal(hasWinner, rows, cols,i, j);
                    rightDiagonal(hasWinner,rows,cols,i,j);

                 });
        return hasWinner[0];
    }

    private void rightDiagonal(boolean[] hasWinner, int rows,int cols, int i, int j) {
        if( i==rows-2
                && j==cols-2
                && ( board[i][j]!=GameSign.Blank
                && board[i+1][j-1]!=GameSign.Blank
                && board[i-1][j+1]!=GameSign.Blank)
                && board[i][j]==board[i+1][j-1]
                && board[i][j]==board[i-1][j+1]) {
            hasWinner[0] = true;
            startWinCoordinates =   new Cell(rows - i, cols - j-2);
            endWinCoordinates   =    new Cell(rows - i-2, cols - j);
        }
    }

    private void leftDiagonal(boolean[] hasWinner, int rows, int cols ,int i, int j) {

        if(i==rows-1 && (i==j)
                && board[i][j]!=GameSign.Blank
                && board[i-1][j-1]!=GameSign.Blank
                && board[i-2][j-2]!=GameSign.Blank
                && board[i][j]==board[i-1][j-1]
                && board[i][j]==board[i-2][j-2]) {
            hasWinner[0] = true;
            startWinCoordinates =   new Cell(rows - i-1,cols-j-1);
            endWinCoordinates   =    new Cell(rows - 1, cols - 1);
        }
    }

    private void column(boolean[] hasWinner, int rows, int i, int j) {
        GameSign firstSign=board[rows-1][j];
        GameSign secondSign=board[rows-2][j];
        GameSign thirdSign=board[rows-3][j];
        if(hasMatchingSigns(firstSign, secondSign, thirdSign)) {
            hasWinner[0] = true;
            startWinCoordinates =   new Cell(i,j);
            endWinCoordinates   =    new Cell(rows -i- 1, j);
        }
    }

    private boolean hasMatchingSigns(GameSign firstSign, GameSign secondSign, GameSign thirdSign) {
        return firstSign!= GameSign.Blank
                && secondSign!=GameSign.Blank
                && thirdSign!=GameSign.Blank
                && firstSign==secondSign
                && firstSign==thirdSign;
    }

    private void row(boolean[] hasWinner, int cols, int i, int j) {
        GameSign firstSign=board[i][cols-1];
        GameSign secondSign=board[i][cols-2];
        GameSign thirdSign=board[i][cols-3];
        if(hasMatchingSigns(firstSign, secondSign, thirdSign)){
            hasWinner[0] = true;
            startWinCoordinates=   new Cell(i,j);
            endWinCoordinates=    new Cell(i, cols-j-1);

        }

    }


    private boolean hasBlank() {
        for (GameSign[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (aBoard[j] == GameSign.Blank) return true;
            }
        }
        return false;
    }

    public boolean isGameEnded() {
        return isTie() || (winner!=null);
    }




}
