package Main;


import Input.Keyboard;
import Input.Mouse;
import UI.Game;
import UI.Menu;

import javax.swing.*;

public class JavGame {
    public static void main(String[] args) {

        JFrame frame = new JFrame("JavGame");
        Game game = new Game();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(new Keyboard());
        frame.addMouseListener(new Mouse());





    }

}
