package main.frame;

import java.util.List;

public interface BaseNodeContext {

    /**
     * 获取总胃容量
     * @return
     */
    int getGastricCapacity();

    /**
     * 还需要吃多少东西
     */
    int getStillNeeded();

    /**
     * 已经吃的内容集合
     */
    List<String> getAlreadyEatItems();
}
