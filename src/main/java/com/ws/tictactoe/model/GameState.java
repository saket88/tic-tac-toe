package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import static java.util.stream.IntStream.range;

@Builder
public class GameState {

    @Getter
    @JsonIgnore
    GameSign[][] board;

    @Getter
    Player nextPlayer;


    @Getter
    private GameSign winner;

    @Getter
    private boolean tie;

    @Getter
    private Move lastMove;

    private WinningPosition winningSequence;

    public void update(Cell cell) {
        initializeGameBoard();
        GameSign[][] gameSigns = getBoard();
        GameSign currentGameSign = getNextPlayer().getGameSign();
        int row = cell.getRow();
        int column = cell.getColumn();
        gameSigns[row][column] = currentGameSign;
        lastMove = new Move(nextPlayer.getGameSign(),new Cell(row, column));
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
                     row(hasWinner, rows ,cols, i ,j);
                     column(hasWinner, rows, cols ,i,j);
                     leftDiagonal(hasWinner, rows, cols,i, j);
                     rightDiagonal(hasWinner,rows,cols,i,j);

        });
        return hasWinner[0];
    }

    private void rightDiagonal(boolean[] hasWinner, int rows,int cols, int i, int j) {
        if(i==rows-2  && j==cols-2 && board[i][j]==board[i+1][j-1] && board[i][j]==board[i-1][j+1]) {
            hasWinner[0] = true;
            winningSequence = new WinningPosition(new Cell(rows - i, cols - j-2),
                    new Cell(rows - i-2, cols - j));
        }
    }

    private void leftDiagonal(boolean[] hasWinner, int rows, int cols ,int i, int j) {
        if(i==rows-1 && (i==j) && board[i][j]==board[i-1][j-1] && board[i][j]==board[i-2][j-2]) {
            hasWinner[0] = true;
            winningSequence = new WinningPosition(new Cell(rows - i-1,cols-j-1),
                    new Cell(rows - 1, cols - 1));
        }
    }

    private void column(boolean[] hasWinner, int rows, int cols ,int i, int j) {
        if(board[rows-1][j]==board[rows-3][j] && board[rows-1][j]==board[rows-2][j]) {
            hasWinner[0] = true;
            winningSequence = new WinningPosition(new Cell(i,j),
                    new Cell(rows -i- 1, j));
        }
    }

    private void row(boolean[] hasWinner, int rows ,int cols, int i, int j) {
        if(board[i][cols-1]==board[i][cols-3] && board[i][cols-1]==board[i][cols-2]){
            hasWinner[0] = true;
            winningSequence = new WinningPosition(new Cell(i,j),
                    new Cell(i, cols-j-1));
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
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == GameSign.Blank) return true;
            }
        }
      return false;
  }

    public WinningPosition getWinningSequence() {
        return winningSequence;
    }

    public boolean isAtEnd() {
        return isTie() || (winner!=null);
    }

}
