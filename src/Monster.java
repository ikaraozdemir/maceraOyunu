import java.util.Random;

public class Monster {
    private int id;
    private String name;
    private int damage;
    private int health;
    private int award;
    private int initHealth;
    private String winingAward ;


    public Monster(int id, String name, int damage, int health, int award, String winingAward ) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.initHealth = health;
        this.award = award;
        this.winingAward = winingAward;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        if(health < 0){
            health = 0;
        }
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getInitHealth() {
        return initHealth;
    }

    public void setInitHealth(int initHealth) {
        this.initHealth = initHealth;
    }

    public String getWiningAward() {
        return winingAward;
    }

    public void setWiningAward(String winingAward) {
        this.winingAward = winingAward;
    }
}
