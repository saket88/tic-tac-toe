package com.ws.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

import static java.util.stream.IntStream.range;


@AllArgsConstructor
@Builder
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE,getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class Game {


    public Game(){

    }
    @Getter
    private final String id = UUID.randomUUID().toString();

    @Getter
    GameSign[][] board;

    @Getter
    Player nextPlayer;


    @Getter
    private GameSign winner;

    @Getter
    private boolean tie;

    @Getter
    private Cell startWinCoordinates;

    @Getter
    private Cell endWinCoordinates;



    public void playTurn(Cell cell) {
        initializeGameBoard();
        GameSign[][] gameSigns = getBoard();
        GameSign currentGameSign = getNextPlayer().getGameSign();
        gameSigns[cell.getRow()][cell.getColumn()] = currentGameSign;
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
        if(i==rows-2  && j==cols-2 && board[i][j]==board[i+1][j-1] && board[i][j]==board[i-1][j+1]) {
            hasWinner[0] = true;
            startWinCoordinates=   new Cell(rows - i, cols - j-2);
            endWinCoordinates=    new Cell(rows - i-2, cols - j);
        }
    }

    private void leftDiagonal(boolean[] hasWinner, int rows, int cols ,int i, int j) {
        if(i==rows-1 && (i==j) && board[i][j]==board[i-1][j-1] && board[i][j]==board[i-2][j-2]) {
            hasWinner[0] = true;
            startWinCoordinates=   new Cell(rows - i-1,cols-j-1);
            endWinCoordinates=    new Cell(rows - 1, cols - 1);
        }
    }

    private void column(boolean[] hasWinner, int rows, int i, int j) {
        if(board[rows-1][j]==board[rows-3][j] && board[rows-1][j]==board[rows-2][j]) {
            hasWinner[0] = true;
            startWinCoordinates=   new Cell(i,j);
            endWinCoordinates=    new Cell(rows -i- 1, j);
        }
    }

    private void row(boolean[] hasWinner, int cols, int i, int j) {
        if(board[i][cols-1]==board[i][cols-3] && board[i][cols-1]==board[i][cols-2]){
            hasWinner[0] = true;
            startWinCoordinates=   new Cell(i,j);
            endWinCoordinates=    new Cell(i, cols-j-1);

        }

    }


    private void initializeGameBoard() {
        if (board == null) {
            board = new GameSign[][]{
                    {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                    {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                    {GameSign.Blank, GameSign.Blank, GameSign.Blank}

            };
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
