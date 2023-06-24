package main.eatflow.nodes;

import lombok.extern.slf4j.Slf4j;
import main.eatflow.context.EatNormalContext;
import main.frame.EatNode;
import main.frame.EatingNodeExcuteResult;

/**
 * 该节点定义了吃苹果的逻辑
 */
@Slf4j
public class AppleNode implements EatNode<EatNormalContext> {  // 此处需要指定类型EatNormalContext
    /**
     * 吃一个苹果可以加1点饱食度
     */
    private int satiety=1;
    private String item="Apple";

    public AppleNode(int satiety) {
        this.satiety = satiety;
    }

    public AppleNode() {
    }

    /**
     * 执行吃苹果逻辑
     *
     * @param context
     * @return
     * @throws Exception
     */
    @Override
    public EatingNodeExcuteResult excute(EatNormalContext context) throws Exception {
        // 假如还能继续吃
        synchronized (context){
            if(context.getStillNeeded()>0){
                log.info("吃苹果");
                // 更新context的内容
                context.addAlreadyEatItems(item);
                int stillNeeded = context.getStillNeeded()-satiety;
                context.setStillNeeded(stillNeeded>0?stillNeeded:0);

                if(stillNeeded<=0){
                    // 吃完这个苹果后已经不能继续吃了
                    log.info("---------------------吃饱了，再吃就不礼貌了---------------------");
                    return EatingNodeExcuteResult.buildSatisfiedResult();
                }
            }
        }


        return EatingNodeExcuteResult.buildUnsatisfiedResult();
    }
}
