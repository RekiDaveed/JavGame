package UI;

import javax.swing.*;
import java.awt.*;

public class SettingsUI extends JPanel {

    public static boolean SoundOn = true;
    public static boolean MusicOn = true;
    public static boolean GameTips = true;
    public static int DifficultyLevel = 1; // 1: Easy, 2: Medium, 3: Hard

    public JButton ToggleSound = new JButton("Sound " + (SoundOn ? "On" : "Off"));
    public JButton ToggleMusic = new JButton("Music: " + (MusicOn ? "On" : "Off"));
    public JButton ToggleGameTips = new JButton("Game Tips: " + (GameTips ? "On" : "Off"));
    public JButton ChangeDifficulty = new JButton("Difficulty Level: " + (DifficultyLevel == 1 ? "Easy" : DifficultyLevel == 2 ? "Medium" : "Hard"));
    public JButton BackToMenu = new JButton("Back to Menu");

    BoxLayout boxLayout = new  BoxLayout(this, BoxLayout.Y_AXIS);

    public SettingsUI(Game maingame) {
        maingame.add(this, "SettingsUI");
        setLayout(boxLayout);
        add(Box.createVerticalGlue());
        add(ToggleSound); ToggleSound.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));

        add(ToggleMusic); ToggleMusic.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));

        add(ToggleGameTips); ToggleGameTips.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));

        add(ChangeDifficulty); ChangeDifficulty.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));

        add(BackToMenu); BackToMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));

        add(Box.createVerticalGlue());
        setBackground(Color.RED);


        ToggleSound.addActionListener(e -> {
            SoundOn = ToggleOption(SoundOn);
            ToggleSound.setText("Sound " + (SoundOn ? "On" : "Off"));
        });

        ToggleMusic.addActionListener(e -> {
            MusicOn = ToggleOption(MusicOn);
            ToggleMusic.setText("Music: " + (MusicOn ? "On" : "Off"));
        });

        ToggleGameTips.addActionListener(e -> {
            GameTips = ToggleOption(GameTips);
            ToggleGameTips.setText("Game Tips: " + (GameTips ? "On" : "Off"));
        });

        ChangeDifficulty.addActionListener(e -> {
            CycleDifficulty();
            ChangeDifficulty.setText("Difficulty Level: " + (DifficultyLevel == 1 ? "Easy" : DifficultyLevel == 2 ? "Medium" : "Hard"));
        });

        BackToMenu.addActionListener(e -> {
            HideSettings(maingame);
        });
    }

    public void ShowSettings(Game maingame) {
        // Implementation to show settings if needed
        System.out.println("Settings Opened");
        maingame.GamecardLayout.show(maingame, "SettingsUI");
        for (Component c : maingame.getComponents()) {
            if (c instanceof Menu) {
                ((Menu) c).SettingsState = 1;
            }
        }
    }

    public void HideSettings(Game maingame) {
        // Implementation to hide settings if needed
        System.out.println("Settings Closed");
        maingame.GamecardLayout.show(maingame, "MENU");
        for (Component c : maingame.getComponents()) {
            if (c instanceof Menu) {
                ((Menu) c).SettingsState = 0;
            }
        }
    }

    public Boolean ToggleOption(Boolean bool) {
        return !bool;
    }

    public void CycleDifficulty() {
        if (DifficultyLevel == 1) {
            DifficultyLevel = 2;
        } else if (DifficultyLevel == 2) {
            DifficultyLevel = 3;
        } else {
            DifficultyLevel = 1;
        }
    }
}
