package Settings;

public enum SFX {
    ON,
    OFF;

    public static SFX gamesfx = ON;

    public static SFX SetSound(SFX s){
        gamesfx = s;
        return s;
    }
}
