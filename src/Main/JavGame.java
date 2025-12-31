package Main;


import Input.Keyboard;
import Input.Mouse;
import UI.Game;
import UI.Menu;

import javax.swing.*;

public class JavGame {
     static void main(String[] args) {

        JFrame frame = new JFrame("____'s Adventure");
        Game game = new Game();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

        game.GameStart();

    }
}
