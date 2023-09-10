package Checkers.CheckerType;

import Checkers.Player.Player;
import Checkers.Player.BlackPlayer;
import Checkers.Player.RedPlayer;

public enum PieceColor {

    RED("RED"){
        @Override
        public int getDirection(){
            return 1;
        }
        @Override
        public boolean isRed(){
            return true;
        }
        @Override
        public boolean isBlack(){
            return false;
        }

        @Override
        public Player choosePlayer(RedPlayer redPlayer, BlackPlayer blackPlayer) {
            return redPlayer;
        }
    },
    BLACK("BLACK"){
        @Override
        public int getDirection(){
            return -1;
        }
        @Override
        public boolean isRed(){
            return false;
        }
        public boolean isBlack(){
            return false;
        }

        @Override
        public Player choosePlayer(RedPlayer redPlayer, BlackPlayer blackPlayer) {
            return blackPlayer;
        }
    };

    private String Color;

    PieceColor(String Color){
        this.Color=Color;
    }
    public String getColorString(){
        return this.Color;
    }

    public abstract int getDirection();
    public abstract boolean isRed();
    public abstract boolean isBlack();


    public abstract Player choosePlayer(RedPlayer redPlayer, BlackPlayer blackPlayer);
}
