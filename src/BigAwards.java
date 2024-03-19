public class BigAwards {
    private int id;
    private String name;

    public BigAwards(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static BigAwards[] bigawards() {
        BigAwards[] awardList = new BigAwards[3];
        awardList[0] = new BigAwards(1,"Food");
        awardList[1] = new BigAwards(2,"Firewood");
        awardList[2] = new BigAwards(3,"Water");
        return awardList;
    }

    public static BigAwards getBigAwById (int id){
        for(BigAwards b : BigAwards.bigawards()){
            if(b.getId() == id){
                return b;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
