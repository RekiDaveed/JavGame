package Main;

public enum G_State {
    Playing,
    Pause,
    Settings,
    MainMenu,
    GameOver;

    public static G_State G_state = MainMenu;

    public static G_State SetState(G_State s){
        G_state  = s;
        return s;
    }

    public void StateAction(){
        switch (G_state){
            case Playing:
                System.out.println("Game is Playing");
                break;
            case Pause:
                System.out.println("Game is Paused");
                break;
            case Settings:
                System.out.println("In Settings Menu");
                break;
            case MainMenu:
                System.out.println("In Main Menu");
                break;
            case GameOver:
                System.out.println("Game Over");
                break;
        }
    }

    public static G_State GetState(){
        return G_state;
    }
}
