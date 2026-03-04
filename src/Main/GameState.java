package Main;

public enum GameState {
    Playing,
    Pause,
    Settings,
    MainMenu,
    GameOver;

    public static GameState gamestate = MainMenu;

    public static GameState SetState(GameState s){
        gamestate = s;
        return s;
    }

    public void StateAction(){
        switch (gamestate){
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

    public static GameState GetState(){
        return gamestate;
    }

    public static GameState SetPlaying(Thread thread){
        gamestate = Playing;
        thread.start();
        return gamestate;
    }
}
