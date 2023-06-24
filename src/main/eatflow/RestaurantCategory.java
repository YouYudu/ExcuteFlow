package main.eatflow;

import lombok.Getter;

@Getter
public enum RestaurantCategory {
    Yonghehui(1, "雍和会");


    private Integer code;
    private String desc;

    RestaurantCategory(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

    public static RestaurantCategory getEnum(String target){
        for(RestaurantCategory item:RestaurantCategory.values()){
            if(item.getDesc().equals(target)){
                return item;
            }
        }
        return null;
    }
}
