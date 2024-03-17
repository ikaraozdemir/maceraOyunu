import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class BattleLoc extends Location{
   private Monster monster;
   private String award;
   private int maxMonster;
   public static String[] bigAwardsList = {"","",""};
   public static List<String> bigAwardsList2 = Arrays.asList(bigAwardsList);
   private static int snakeDroppedId;

    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monsNumber = this.randomMonsterNum();
        System.out.println("Şu an buradasınız: " + this.getName());
        System.out.println("Dikkatli ol! Burada " + monsNumber + " tane " + this.getMonster().getName() + " yaşıyor.");
        System.out.print("<S>avaş veya <K>aç: ");
        String selectMove = input.nextLine();
        selectMove = selectMove.toUpperCase();

        if (selectMove.equals("S") && combat(monsNumber)){
            System.out.println(this.getName() +" bölgesindeki tüm düşmanları yendiniz!");
            if (getMonster().getName().equals("Yılan")){
                whichAward();
            }

            if(!bigAwardsList2.contains("")){
                System.out.println("Tebrikler, oyunu başarıyla tamamladınız!");
                return false;
            }
            return true;
        }

        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz.");
            return false;
        }
        return true;
    }

    public boolean combat (int monsNumber){
        for (int i = 1; i <= monsNumber; i++){
            this.getMonster().setHealth(this.getMonster().getInitHealth());
            playerStats();
            monsterStats(i);
            while (this.getMonster().getHealth() > 0 && this.getPlayer().getHealth() > 0){
                System.out.print("<V>ur veya <K>aç: ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")){
                    double chanceHit = Math.random();
                    if (chanceHit < 0.5){
                        System.out.println("Siz vurdunuz.");
                        this.getMonster().setHealth(this.getMonster().getHealth()- this.getPlayer().getTotalDamage());
                        afterHit();
                    } else {
                        if (this.getMonster().getHealth() > 0) {
                            System.out.println("Düşman sizden önce saldırdı!");
                            int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (monsterDamage < 0) {
                                monsterDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                            afterHit();
                        }
                    }
                } else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz!");
                System.out.println(this.getMonster().getAward() + " birim kadar para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward() );
                if (i == monsNumber && !getMonster().getName().equals("Yılan")){
                    if (this.getMonster().getId() == BigAwards.getBigAwById(this.getMonster().getId()).getId()){
                        bigAwardsList[this.getMonster().getId()-1] = BigAwards.getBigAwById(this.getMonster().getId()).getName();
                        System.out.println("Tebrikler " + BigAwards.getBigAwById(this.getMonster().getId()).getName() +
                                " kazandınız! Oyunu bitirmeye bir adım daha yaklaştınız." );
                    }
                }
            } else {
                return false;
            }
        }
        return true;
    }
    public void whichAward(){
        double probInv = Math.random();
        double probInvName = Math.random();

        if (getMonster().getHealth()<= 0){
            if (probInv < 0.15){
                if (probInvName < 0.20){
                    setSnakeDroppedId(3);
                    collectWeapon();
                } else if (probInvName < 0.50) {
                    setSnakeDroppedId(2);
                    collectWeapon();
                } else {
                    setSnakeDroppedId(1);
                    collectWeapon();
                }
            } else if (probInv < 0.30) {
                if (probInvName < 0.20){
                    setSnakeDroppedId(3);
                    collectArmor();
                } else if (probInvName < 0.50) {
                    setSnakeDroppedId(2);
                    collectArmor();
                } else {
                    setSnakeDroppedId(1);
                    collectArmor();
                }
            } else if (probInv < 0.55) {
                System.out.println("Yılan para düşürdü!");
                if (probInvName < 0.20){
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                } else if (probInvName < 0.50) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                } else {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                }
            }
        }
    }

    public void collectWeapon(){
        System.out.println("-------------------------------------");
        System.out.println("Yılan bir silah düşürdü!");
        System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
        this.getPlayer().getInventory().setWeapon(Weapon.getWeaponById(Mine.getSnakeDroppedId()));
        System.out.println("Güncel silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
    }

    public void collectArmor(){
        System.out.println("-------------------------------------");
        System.out.println("Yılan bir zırh düşürdü!");
        System.out.println("Önceki zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
        this.getPlayer().getInventory().setArmor(Armor.getArmornById(Mine.getSnakeDroppedId()));
        System.out.println("Güncel zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
    }

    public void playerStats(){
        System.out.println( "Oyuncu Değerleri");
        System.out.println("-------------------------------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: " + this.getPlayer().getInventory().getWeapon().getName() );
        System.out.println("Hasar: " + this.getPlayer().getTotalDamage());
        System.out.println("Zırh: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para: " + this.getPlayer().getMoney());

        System.out.println();
    }

        public void monsterStats(int i){
        System.out.println(i + ". " + this.getMonster().getName() + " Değerleri");
        System.out.println("-------------------------------------");
        System.out.println("Sağlık: " + this.getMonster().getHealth());
        System.out.println("Hasar: " + this.getMonster().getDamage() );
        System.out.println("Ödül: " + this.getMonster().getAward());
        System.out.println("Büyük Ödül: " + this.getMonster().getWiningAward());
        System.out.println();
    }

    public void afterHit() {
        System.out.println("-------------------------------------");
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() +  " Canı: " + this.getMonster().getHealth());
        System.out.println();
    }

    public int randomMonsterNum (){
        Random r = new Random();
        return r.nextInt(this.getMaxMonster()) + 1;

    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }



    public static int getSnakeDroppedId() {
        return snakeDroppedId;
    }

    public static void setSnakeDroppedId(int snakeDroppedId) {
        Mine.setSnakeDroppedId(snakeDroppedId);
    }
}
