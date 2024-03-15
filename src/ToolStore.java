public class ToolStore extends NormalLoc {

    public ToolStore(Player player) {
        super(player, "Alet Dükkanı");
    }

    @Override
    public boolean onLocation() {
        boolean showMenu = true;
        while (showMenu){
            System.out.println("Alet dükkanına hoşgeldiniz.");
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int selectTool = input.nextInt();
            while (selectTool < 1 || selectTool > 3 ){
                System.out.print("Geçersiz değer, tekrar giriniz: ");
                selectTool = input.nextInt();
            }
            switch (selectTool){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Yine bekleriz.");
                    showMenu = false;
            }
        }

        return true;
    }

    public void printWeapon(){
        System.out.println("Silahlar");
        for(Weapon weapon : Weapon.weapons()){
            System.out.println("ID: " + weapon.getId() + " " + weapon.getName() +
                    "\tHasar: " + weapon.getDamage() + "\tFiyat: " + weapon.getPrice());
        }
        System.out.println("0 - Çıkış yap");
    }

    public void buyWeapon(){
        System.out.print("Bir silah seçiniz: ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length ){
            System.out.print("Geçersiz değer, tekrar giriniz: ");
            selectWeaponID = input.nextInt();
        }
        if(selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponById(selectWeaponID);
            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Bakiye yetersiz");
                }else{
                    System.out.println(selectedWeapon.getName() + "silahını satın aldınız");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Güncel silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }

    public void printArmor(){
        System.out.println("Zırhlar");
        for(Armor armor : Armor.armors()){
            System.out.println("ID: " + armor.getId() + " " + armor.getName() +
                    "\tKoruma: " + armor.getBlock() + "\tFiyat: " + armor.getPrice());
        }
        System.out.println("0 - Çıkış yap");
    }
    public void buyArmor(){
        System.out.print("Bir zırh seçiniz: ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Weapon.weapons().length ){
            System.out.print("Geçersiz değer, tekrar giriniz: ");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmornById(selectArmorID);

            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Bakiye yetersiz");
                }else{
                    System.out.println(selectedArmor.getName() + "zırhı satın aldınız");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Güncel zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                }

            }
        }
    }

}
