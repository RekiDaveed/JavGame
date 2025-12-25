package UI;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{

    JButton Play = new JButton("Play");
    JButton Settings = new JButton("Settings");
    JButton Help = new JButton("Help");
    JButton Quit = new JButton("Quit");
    public int SettingsState = 0; // 0 = closed, 1 = open

    public int TileSize = 38;
    public int TileXCount = 20;
    public int TileYCount = 16;

    public int JFrameWidth = TileXCount * TileSize;
    public int JFrameHeight = TileYCount * TileSize;

    public Menu(Game maingame){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalGlue());

        // Configure and add Play button
        Play.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Play);
        add(Box.createVerticalStrut(20));

        // Configure and add Settings button
        Settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Settings);
        add(Box.createVerticalStrut(20));

        Help.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Help);
        add(Box.createVerticalStrut(20));

        // Configure and add Quit button
        Quit.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Quit);

        // Add glue at the bottom to keep buttons centered vertically
        add(Box.createVerticalGlue());

        setBackground(Color.CYAN);

        maingame.add(this, "MENU");
        SettingsUI settingsUI = new SettingsUI(maingame);

        setPreferredSize(new Dimension(JFrameWidth, JFrameHeight));
        revalidate();
        setVisible(true);

        Play.addActionListener(e -> maingame.GameStart());

        Settings.addActionListener(e -> {
            if (SettingsState == 0){
                SettingsState = 1;
                settingsUI.ShowSettings(maingame);
            } else {
                SettingsState = 0;
                settingsUI.HideSettings(maingame);
            }
        });

        Help.addActionListener(e -> {
            JOptionPane.showMessageDialog(this,
                    """
                            Game Instructions:
                            1. Use arrow keys to move.
                            2. Collect items to score points.
                            3. Avoid obstacles to stay alive.
                            4. Press 'P' to pause the game.
                            5. Have fun!""",
                    "How To Play",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        Quit.addActionListener(e -> System.exit(0));
    }
}
