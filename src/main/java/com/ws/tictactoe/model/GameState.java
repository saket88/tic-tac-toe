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

    @Getter
    private Move lastMove;

    private WinningPosition winningSequence;

    public void update(Cell cell) {
        initialiazeGameBoard();
        GameSign[][] gameSigns = getBoard();
        GameSign currentGameSign = getNextPlayer().getGameSign();
        int row = cell.getRow();
        int column = cell.getColumn();
        gameSigns[row][column] = currentGameSign;
        lastMove = new Move(nextPlayer.getGameSign(),new Cell(row, column));
        nextPlayer = new Player(nextPlayer.getGameSign().toggle().name());
        determineResult(currentGameSign, row, column);

    }


    private void determineResult(GameSign currentGameSign, int row, int column) {
        if (hasWinnerFor(currentGameSign, row, column)) {
            winner = currentGameSign;
        }
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

        int topleftRowIndex = 0;
        int topleftColumnIndex = 0;
        int toprightRowIndex = 0;
        int toprightColumnIndex = 0;

        int bottomleftRowIndex = 0;
        int bottomleftColumnIndex = 0;
        int bottomrightRowIndex = 0;
        int bottomrightColumnIndex = 0;

        int leftRowIndex = 0;
        int leftColumnIndex = 0;
        int rightRowIndex = 0;
        int rightColumnIndex = 0;

        int topRowIndex = 0;
        int topColumnIndex = 0;
        int bottomRowIndex = 0;
        int bottomColumnIndex = 0;


        for (int i = row-1, j = col-1; i >= 0 && j >= 0 ; i--, j--) {
            if (board[i][j] == charAtPosition) {
                bottomleft++;
                bottomleftRowIndex=i;
                bottomleftColumnIndex=j;
            } else {
                break;
            }
        }
        for (int i = row-1, j = col+1; i >= 0 && j < numCols; i--, j++) {
            if (board[i][j] == charAtPosition) {
                topleft++;
                topleftRowIndex=i;
                topleftColumnIndex=j;
            } else {
                break;
            }
        }
        for (int i = row+1, j = col-1; i < numRows && j >= 0 ; i++, j--) {
            if (board[i][j] == charAtPosition) {
                bottomright++;
                bottomrightRowIndex=i;
            } else {
                bottomrightColumnIndex=j;
                break;
            }
        }
        for (int i = row+1, j = col+1; i < numRows && j < numCols ; i++, j++) {
            if (board[i][j] == charAtPosition) {
                topright++;
                toprightRowIndex=i;
                toprightColumnIndex=j;
            } else {
                break;
            }
        }

        for (int i = row, j = col+1; j < numCols ; j++) {
            if (board[i][j] == charAtPosition) {
                right++;
                rightRowIndex=i;
                rightColumnIndex=j;
            } else {
                break;
            }
        }

        for (int i = row+1, j = col; i < numRows ; i++) {
            if (board[i][j] == charAtPosition) {
                top++;
                topRowIndex=i;
                topColumnIndex=j;
            } else {
                break;
            }
        }

        for (int i = row, j = col-1; j >=0 ;  j--) {
            if (board[i][j] == charAtPosition) {
                left++;
                leftRowIndex=i;
                leftColumnIndex=j;
            } else {
                break;
            }
        }

        for (int i = row-1, j = col; i >=0 ; i--) {
            if (board[i][j] == charAtPosition) {
                bottom++;
                bottomRowIndex=i;
                bottomColumnIndex=j;
            } else {
                break;
            }
        }

        if(topleft + bottomright +1 >=numRows ){

            if(topleftRowIndex==0 && topleftColumnIndex ==0){
                topleftRowIndex = row;
                topleftColumnIndex =col;
            }

            else if(bottomrightRowIndex == 0 && bottomrightColumnIndex == 0){
                bottomrightRowIndex = row;
                bottomrightColumnIndex = col;
            }
            winningSequence = new WinningPosition(new Cell(topleftRowIndex,topleftColumnIndex),
                    new Cell(bottomrightRowIndex,bottomrightColumnIndex));
            return true;
        }

        if(topright + bottomleft +1 >=numCols){
            if(bottomleftRowIndex==0 && bottomleftColumnIndex ==0){
                bottomleftRowIndex = row;
                bottomleftColumnIndex =col;
            }

            else if(toprightRowIndex == 0 && toprightColumnIndex == 0){
                toprightRowIndex = row;
                toprightColumnIndex = col;
            }
            winningSequence = new WinningPosition(new Cell(bottomleftRowIndex,bottomleftColumnIndex),
                    new Cell(toprightRowIndex,toprightColumnIndex));
            return true;
        }

        if(top+bottom +1>=numCols){

            if(topRowIndex==0 && topColumnIndex ==0){
                topRowIndex = row;
                topColumnIndex =col;
            }

            else if(bottomRowIndex == 0 && bottomColumnIndex == 0){
                bottomRowIndex = row;
                bottomColumnIndex = col;
            }
            winningSequence = new WinningPosition(new Cell(topRowIndex,topColumnIndex),
                    new Cell(bottomRowIndex,bottomColumnIndex));
            return true;
        }

        if(left+right +1>=numRows){
            if(leftRowIndex==0 && leftColumnIndex ==0){
                leftRowIndex = row;
                leftColumnIndex =col;
            }

            else if(rightRowIndex == 0 && rightColumnIndex == 0){
                rightRowIndex = row;
                rightColumnIndex = col;
            }
            winningSequence = new WinningPosition(new Cell(leftRowIndex,leftColumnIndex),
                    new Cell(rightRowIndex,rightColumnIndex));
            return true;
        }
        return false;
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

    public WinningPosition getWinningSequence() {
        return winningSequence;
    }

    public boolean isAtEnd() {
        return isTie() || (winner!=null);
    }

}
