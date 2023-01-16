package src.ui.gui;


import java.util.LinkedList;

public class PieceGraphics {

    int xpos;
    int ypos;

    public PieceGraphics(int xpos, int ypos, boolean isWhite, LinkedList<PieceGraphics> piece) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.isWhite = isWhite;

    }

    boolean isWhite;



    public void move(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;

    }
}
