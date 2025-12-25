package Settings;

public enum S_Sound {
    ON,
    OFF;

    public static S_Sound S_sound = ON;

    public static S_Sound SetSound(S_Sound s){
        S_sound  = s;
        return s;
    }
}
