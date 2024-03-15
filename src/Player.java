import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int initHealth;
    private int money;
    private String name;
    private String charName;
    private Scanner input = new Scanner (System.in);
    private Inventory inventory;


    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){

        GameChar[] gameChars = {new Samurai(), new Archer(), new Knight()}; //Polymorphism
        System.out.println("Karakterler");
        System.out.println("--------------------------");

        for (GameChar  gameChar : gameChars){
            System.out.println( "ID: "+ gameChar.getId() +
                    "\tKarakter: " + gameChar.getName()  +
                    "\tHasar: " + gameChar.getDamage() +
                    "\tSağlık: " + gameChar.getHealth() +
                    "\tPara " + gameChar.getMoney());
        }
        System.out.println("--------------------------");
        System.out.print("Lütfen bir karakter seçiniz: ");
        int selectChar = input.nextInt();

        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
//        System.out.println("Karakter: " + this.getCharName() +
//                "\tHasar: " + this.getDamage() +
//                "\tSağlık: " + this.getHealth() +
//                "\tPara: " + this.getMoney());
    }

    public void initPlayer(GameChar gameChar){ //Polymorphism
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setInitHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
    }

    public void printInfo(){
        System.out.println("Karakter: " + this.getCharName() +
                "\tSilahınız: " + this.getInventory().getWeapon().getName() +
                "\tZırhınız: " + this.getInventory().getArmor().getName() +
                "\tBloklama: " + this.getInventory().getArmor().getBlock() +
                "\tHasar: " + this.getTotalDamage() +
                "\tSağlık: " + this.getHealth() +
                "\tPara: " + this.getMoney());
    }

    public int getTotalDamage () {
        return this.getDamage() + getInventory().getWeapon().getDamage();
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String name) {
        this.charName = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getInitHealth() {
        return initHealth;
    }

    public void setInitHealth(int initHealth) {
        this.initHealth = initHealth;
    }
}
