package cz.schovjan.pexeso.model;

/**
 *
 * @author schovjan
 */
public class Game {

    private Player player1;
    private Player player2;
    private int playerOnMove;
    private int countOfMove;

    public Game() {
        playerOnMove = 0;
        countOfMove = 0;
        player1 = new Player();
        player2 = new Player();
    }

    public int getPlayerOnMove() {
        return playerOnMove;
    }

    public void setPlayerOnMove(int playerOnMove) {
        this.playerOnMove = playerOnMove;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getCountOfMove() {
        return countOfMove;
    }

    public void setCountOfMove(int countOfMove) {
        this.countOfMove = countOfMove;
    }

    public int moveIncrement() {
        return ++this.countOfMove;
    }

    public int playerOnMoveIncrement() {
        return ++this.playerOnMove;
    }

    public int getTurnedPairsCount() {
        return player1.getScore() + player2.getScore();
    }

    public int getDifferentOfScore() {
        return Math.max(getPlayer1().getScore(), getPlayer2().getScore()) - Math.min(getPlayer1().getScore(), getPlayer2().getScore());
    }
}
