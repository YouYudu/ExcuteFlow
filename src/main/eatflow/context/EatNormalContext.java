package main.eatflow.context;

import main.frame.BaseNodeContext;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通的吃的Context就是对基础接口BaseNodeContext的实现
 *
 * 还可以在这基础上拓展一些高级的吃法，比如吃东西加精神值。。精神值没满也要继续吃
 */
public class EatNormalContext implements BaseNodeContext {
    private int GastricCapacity;
    private int StillNeeded;
    private List<String> AlreadyEatItems;

    /**
     * @param gastricCapacity 总的胃容量
     */
    public EatNormalContext(int gastricCapacity) {
        GastricCapacity = gastricCapacity;
        StillNeeded = gastricCapacity;
        AlreadyEatItems = new ArrayList<>();
    }

    /**
     * 获取总胃容量
     *
     * @return
     */
    public int getGastricCapacity() {
        return GastricCapacity;
    }

    /**
     * 还需要吃多少东西
     */
    public int getStillNeeded() {
        return StillNeeded;
    }

    /**
     * 已经吃的内容集合
     */
    public List<String> getAlreadyEatItems() {
        return AlreadyEatItems;
    }


    public void setGastricCapacity(int gastricCapacity) {
        GastricCapacity = gastricCapacity;
    }

    public void setStillNeeded(int stillNeeded) {
        StillNeeded = stillNeeded;
    }

    public void addAlreadyEatItems(String item) {
        AlreadyEatItems.add(item);
    }
}
