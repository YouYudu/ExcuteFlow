package main.eatflow;

import lombok.extern.slf4j.Slf4j;
import main.idl.EatRequest;

import java.util.Optional;

/**
 * 一个简单工厂，用来选择去哪家饭店吃饭
 */
@Slf4j
public class EatFlowFactory {
    private YonghehuiEatFlow yonghehuiEatFlow;

    public EatFlowFactory() {
        this.yonghehuiEatFlow = new YonghehuiEatFlow();
    }

    public EatFlow getEatFlow(EatRequest request){
        RestaurantCategory restaurantCategory = Optional.ofNullable(RestaurantCategory.getEnum(request.getRestaurant()))
                .orElseThrow(()-> new RuntimeException("未知的餐厅"));

        switch (restaurantCategory){
            case Yonghehui:
                log.info("---------------------来雍和会吃饭了---------------------");
                return yonghehuiEatFlow;
            default:
                throw new RuntimeException("未知的饭店");
        }
    }
}
