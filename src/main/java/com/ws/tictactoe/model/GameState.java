package com.ws.tictactoe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

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

    public void update(Cell cell) {
        initialiazeGameBoard();
        GameSign[][] gameSigns = getBoard();
        GameSign currentGameSign = getNextPlayer().getGameSign();
        int row = cell.getRow();
        int column = cell.getColumn();
        gameSigns[row][column] = currentGameSign;
        nextPlayer = new Player(nextPlayer.getGameSign().toggle().name(),new Cell(row, column));
        determineResult(currentGameSign, row, column);
    }


    private void determineResult(GameSign currentGameSign, int row, int column) {
        if (hasWinnerFor(currentGameSign, row, column))
            winner = currentGameSign;
        else if (!hasBlank()) {
            tie = true;
        }
    }

    private boolean hasWinnerFor(GameSign charAtPosition, int row, int col) {

        int numRows = board.length;
        int numCols = board[0].length;
        int topleft = 0;
        int topright = 0;
        int bottomleft = 0;
        int bottomright = 0;

        int left = 0;
        int right = 0;
        int top = 0;
        int bottom = 0;

        for (int i = row-1, j = col-1; i >= 0 && j >= 0 ; i--, j--) {
            if (board[i][j] == charAtPosition) {
                bottomleft++;
            } else {
                break;
            }
        }
        for (int i = row-1, j = col+1; i >= 0 && j < numCols; i--, j++) {
            if (board[i][j] == charAtPosition) {
                topleft++;
            } else {
                break;
            }
        }
        for (int i = row+1, j = col-1; i < numRows && j >= 0 ; i++, j--) {
            if (board[i][j] == charAtPosition) {
                bottomright++;
            } else {
                break;
            }
        }
        for (int i = row+1, j = col+1; i < numRows && j < numCols ; i++, j++) {
            if (board[i][j] == charAtPosition) {
                topright++;
            } else {
                break;
            }
        }

        for (int i = row, j = col+1; j < numCols ; j++) {
            if (board[i][j] == charAtPosition) {
                right++;
            } else {
                break;
            }
        }

        for (int i = row+1, j = col; i < numRows ; i++) {
            if (board[i][j] == charAtPosition) {
                top++;
            } else {
                break;
            }
        }

        for (int i = row, j = col-1; j >=0 ;  j--) {
            if (board[i][j] == charAtPosition) {
                left++;
            } else {
                break;
            }
        }

        for (int i = row-1, j = col; i >=0 ; i--) {
            if (board[i][j] == charAtPosition) {
                bottom++;
            } else {
                break;
            }
        }
        return topleft + bottomright +1 >=numRows || topright + bottomleft +1>= numCols
                || left+right +1>=numRows||top+bottom +1>=numCols;
    }


    private void initialiazeGameBoard() {
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
}
