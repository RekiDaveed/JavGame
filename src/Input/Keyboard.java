package Input;

import Main.G_State;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public boolean Uppressed, Spressed, Apressed, Dpressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                Uppressed = true;
                break;
            case KeyEvent.VK_S:
                Spressed = true;
                break;
            case KeyEvent.VK_A:
                Apressed = true;
                break;
            case KeyEvent.VK_D:
                Dpressed = true;
                break;
            default:
                break;
        }

        if (KeyEvent.VK_SPACE == key) {
            if (G_State.GetState() == G_State.Playing) {
                G_State.SetState(G_State.Pause);
                System.out.println("Game Paused");
            } else if (G_State.GetState() == G_State.Pause) {
                G_State.SetState(G_State.Playing);
                System.out.println("Game Resumed");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                Uppressed = false;
                break;
            case KeyEvent.VK_S:
                Spressed = false;
                break;
            case KeyEvent.VK_A:
                Apressed = false;
                break;
            case KeyEvent.VK_D:
                Dpressed = false;
                break;
            default:
                break;
        }
    }
}
