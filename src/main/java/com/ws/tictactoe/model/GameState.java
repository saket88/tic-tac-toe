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

    public void update(Cell cell) {
        initialiazeGameBoard();
        GameSign[][] gameSigns = getBoard();
        GameSign currentGameSign = getNextPlayer().getGameSign();
        gameSigns[cell.getRow()][cell.getColumn()]= currentGameSign;
        nextPlayer=new Player(nextPlayer.getGameSign().toggle().name());
        if(hasResultFor(currentGameSign,cell.getRow(),cell.getColumn()))
            winner=currentGameSign;
    }

    private boolean hasResultFor(GameSign charAtPosition,int row,int col) {

        int numRows = board.length;
        int numCols = board[0].length;
        int topleft = 0;
        int topright = 0;
        int bottomleft = 0;
        int bottomright = 0;

        for (int i=row,j=col;i>=0 && j>=0;i--,j--) {
            if (board[i][j]==charAtPosition) {
                topleft++;
            } else {
                break;
            }
        }
        for (int i=row,j=col+2;i>=0 && j<numCols;i--,j++) {
            if (board[i][j]==charAtPosition) {
                topright++;
            } else {
                break;
            }
        }
        for (int i=row+2,j=col;i<numRows && j>=0;i++,j--) {
            if (board[i][j]==charAtPosition) {
                bottomleft++;
            } else {
                break;
            }
        }
        for (int i=row+2,j=col+2;i<numRows && j<numCols;i++,j++) {
            if (board[i][j]==charAtPosition) {
                bottomright++;
            } else {
                break;
            }
        }
        return topleft + bottomright + 1 >= numRows || topright + bottomleft + 1 >= numCols;
    }


    private void initialiazeGameBoard() {
        if(board==null)
        board= new GameSign[][]{
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank},
                {GameSign.Blank, GameSign.Blank, GameSign.Blank}

        };
    }


}
