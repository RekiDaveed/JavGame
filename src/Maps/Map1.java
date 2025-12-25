package Maps;

import UI.Game;

import javax.swing.*;
import java.awt.*;

public class Map1 extends JPanel {
    // Home of the first map
    // IF player dies and Chooses to restart, they will be sent back here

    public Map1(Game mainpanel) {
        mainpanel.add(this, "MAP1");
        mainpanel.revalidate();
        mainpanel.repaint();
        mainpanel.GamecardLayout.show(mainpanel, "MAP1");


    }
}


