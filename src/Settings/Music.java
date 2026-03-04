package Settings;

public enum Music {
    ON,
    OFF;

    public static Music music = ON;

    public static Music setmusic(Music s){
        music = s;
        return s;
    }
}
