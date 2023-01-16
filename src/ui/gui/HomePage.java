package src.ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HomePage extends JFrame implements ActionListener {

    JPanel panel;
    JButton start;
    JButton quit;
    Icon img;

    public static void main(String args[]) throws IOException {
        new HomePage();
    }

    public HomePage() throws IOException {
        drawImage();
        makeStartButton();
        makeQuitButton();
        addAllComponents();
        init();
    }

    public void drawImage()  {
        img = new ImageIcon("src/chessPhoto.png");
        JLabel contentPane = new JLabel();
        contentPane.setIcon( img );
        contentPane.setLayout( new BorderLayout() );
        setContentPane( contentPane );
    }

    public void init() {
        setSize(512, 512);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    public void makeStartButton() {
        start = new JButton();
        start.setBounds(200, 200, 90, 30);
        start.setText("Start");
        start.setFocusable(false);
        start.addActionListener(this);
    }

    public void makeQuitButton() {
        quit = new JButton();
        quit.setBounds(420, 450, 90, 30);
        quit.setText("Quit");
        quit.setFocusable(false);
        quit.addActionListener(e -> System.exit(0));

    }

    public void addAllComponents() {
        add(start);
        add(quit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            this.dispose();
            new BoardGraphics();
        }
    }
}
