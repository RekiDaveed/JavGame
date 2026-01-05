package Main;

import UI.Game;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class JavGame {

    static BufferedImage ImageIcon;
     public static void main(String[] args) throws IOException {

        JFrame frame = new JFrame("Type Soul is Real");
        Game game = new Game();

        ImageIcon = ImageIO.read(Objects.requireNonNull(JavGame.class.getResourceAsStream("/Pack/FreeUi/img_1.png")));

        frame.setIconImage(ImageIcon);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);


        game.GameStart();

    }
}
