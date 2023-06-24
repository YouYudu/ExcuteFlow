package main.idl;

/**
 * 定义了请求的格式
 */
public class EatRequest {

    /**
     * 告知胃容量的大小
     */
    int gastricCapacity;

    /**
     * 去哪个餐厅
     */
    String restaurant;

    public int getGastricCapacity() {
        return gastricCapacity;
    }

    public String getRestaurant() {
        return restaurant;
    }


    public EatRequest(int gastricCapacity, String restaurant) {
        this.gastricCapacity = gastricCapacity;
        this.restaurant = restaurant;
    }

}
