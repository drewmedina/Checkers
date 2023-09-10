package Checkers.CheckerType;

public enum CheckerSymbol {

    MAN("M"),
    KING("K");

    private String checkerLetter;
    CheckerSymbol(final String checkerLetter){
        this.checkerLetter = checkerLetter;
    }
    @Override
    public String toString(){
        return this.checkerLetter;
    }
}
