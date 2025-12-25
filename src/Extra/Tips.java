package Extra;
import java.util.ArrayList;
import java.util.List;

public class Tips {


    List<String> tips = new ArrayList<String>();

    protected String tip1 = " Use arrow keys to navigate the snake.";
    protected String tip2 = " Eat food to grow longer and score points.";
    protected String tip3 = " Avoid running into walls or yourself.";
    protected String tip4 = " Plan your moves ahead to avoid traps.";

    public String getTip(int tipNumber) {
        return switch (tipNumber) {
            case 1 -> tip1;
            case 2 -> tip2;
            case 3 -> tip3;
            case 4 -> tip4;
            default -> "I Made this for fun.";
        };
    }

    public Tips() {
        tips.add(tip1);
        tips.add(tip2);
        tips.add(tip3);
        tips.add(tip4);
    }

    public String GetRandomTip() {
        int randomIndex = (int) (Math.random() * tips.size());
        return tips.get(randomIndex);
    }


}
