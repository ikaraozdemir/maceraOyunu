import java.util.Random;

public abstract class BattleLoc extends Location{
   private Monster monster;
   private String award;
   private int maxMonster;

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
        System.out.print("<S>avaş veya <K>aç");
        String selectMove = input.nextLine();
        selectMove = selectMove.toUpperCase();
        if (selectMove.equals("S") && combat(monsNumber)){
            System.out.println(this.getName() +"tüm düşmanları yendiniz !");
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
                System.out.println("<V>ur veya <K>aç: ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")){
                    System.out.println("Siz vurdunuz.");
                    this.getMonster().setHealth(this.getMonster().getHealth()- this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth()>0){
                        System.out.println("Canavar sizee vurdu.");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage<0){
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-monsterDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz!");
                System.out.println(this.getMonster().getAward() + " birim kadar para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward() );
                System.out.println("Güncel paranız: " + this.getPlayer().getMoney());
            } else {
                return false;
            }

        }
        return true;
    }

    public void playerStats(){
        //       System.out.println( "Oyuncu Değerleri");
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
        System.out.println();
    }

    public void afterHit() {
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
}
