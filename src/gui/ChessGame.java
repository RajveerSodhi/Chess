package src.gui;

import javax.swing.*;
import java.awt.*;

public class ChessGame {
    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setBounds(10,10,512,512);
        JPanel panel = new JPanel(){
            @Override
            public void paint(Graphics g) {
                boolean isWhite = false;
                for(int i = 0; i < 8;i++){
                    for(int j = 0;j<8;j++){
                        if(isWhite){
                            g.setColor(Color.white);
                        } else{
                            g.setColor(Color.black);
                        }
                        g.fillRect(i*64,j*64,64,64);
                        isWhite = !isWhite;

                    }
                    isWhite = !isWhite;
                }
            }
        };
        frame.add(panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
