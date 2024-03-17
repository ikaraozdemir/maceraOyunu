import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner (System.in);
    public void start(){
        System.out.println("\t\tMacera Oyununa Hoşgeldiniz!");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Hoşgeldiniz " + player.getName());
        player.selectChar();
        Location location = null;
        int selectLoc;

        while (true ) {
            player.printInfo();
            do {
                System.out.println("\n############################");
                System.out.println("Bölgeler");
                System.out.println("--------------------------");
                System.out.println("1 - Güvenli Ev");
                System.out.println("2 - Alet Dükkanı");
                System.out.println("3 - Mağara");
                System.out.println("4 - Orman");
                System.out.println("5 - Nehir");
                System.out.println("6 - Maden");
                System.out.println("0 - Çıkış Yap");
                System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
                selectLoc = input.nextInt();
                System.out.println("############################");
                switch (selectLoc) {
                    case 0:
                        location = null;
                        break;
                    case 1:
                        location = new SafeHouse(player);
                        break;
                    case 2:
                        location = new ToolStore(player);
                        break;
                    case 3:
                        if (BattleLoc.bigAwardsList2.contains("Food")){
                            System.out.println("Bu bölgeyi temizlediniz. Yeni bölge seçin:");
                            selectLoc = 1000;
                        }
                        location = new Cave(player);
                        break;
                    case 4:
                        if (BattleLoc.bigAwardsList2.contains("Firewood")){
                            System.out.println("Bu bölgeyi temizlediniz. Yeni bölge seçin:");
                            selectLoc = 1000;
                        }
                        location = new Forest(player);
                        break;
                    case 5:
                        if (BattleLoc.bigAwardsList2.contains("Water")){
                            System.out.println("Bu bölgeyi temizlediniz. Yeni bölge seçin:");
                            selectLoc = 1000;
                        }
                        location = new River(player);
                        break;
                    case 6:
                        location = new Mine(player);
                        break;
                    default:
                        System.out.println("Lütfen geçerli bir bölge giriniz!");
                }
            } while (selectLoc < 0 || selectLoc > 6);


            if (location == null){
                System.out.println("Oyundan ayrıldığınız için üzgünüz :( Tekrar görüşmek üzere... ");
                break;
            }

            if(!location.onLocation()){
                System.out.println("Oyun Bitti!");
                break;
            }
        }
    }
}
