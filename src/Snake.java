import java.util.Random;

public class Snake extends Monster {
    public static Random random = new Random();
    public static int randomDamage = random.nextInt(3) + 3;

    public Snake() {
        super(4, "Yılan", randomDamage, 12, 0, "Silah, Zırh ya da Para");
    }


}
