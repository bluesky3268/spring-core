package hyunbenny.springcore.singleton;

public class StatefulService {
    
    private int price; // 상태를 유지하는 필드

    /**
     *  this.price = price 여기서 문제가 발생하는데 무상태로 설계를 하면 V2와 같다.
     */
//    public void order(String name, int price) {
//        this.price = price;
//        System.out.println("name : " + name + ", price : " + price);
//    }

    /**
     * V2
     */
    public int order(String name, int price) {
        System.out.println("name : " + name + ", price : " + price);
        return price;
    }

//    public int getPrice() {
//        return this.price;
//    }
}
