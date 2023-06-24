package main.eatflow.nodes;

import lombok.extern.slf4j.Slf4j;
import main.eatflow.context.EatNormalContext;
import main.frame.EatNode;
import main.frame.EatingNodeExcuteResult;
@Slf4j
public class PuddingNode implements EatNode<EatNormalContext> {
    /**
     * 吃布丁可以加1点饱食度
     */
    private int satiety=1;
    private String item="Pudding";

    public PuddingNode(int satiety) {
        this.satiety = satiety;
    }
    public PuddingNode() {}

    /**
     * 执行吃布丁逻辑
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
                log.info("吃布丁");
                // 更新context的内容
                context.addAlreadyEatItems(item);
                int stillNeeded = context.getStillNeeded()-satiety;
                context.setStillNeeded(stillNeeded>0?stillNeeded:0);

                if(stillNeeded<=0){
                    // 吃完后已经不能继续吃了
                    log.info("---------------------吃饱了，再吃就不礼貌了---------------------");
                    return EatingNodeExcuteResult.buildSatisfiedResult();
                }
            }
        }


        return EatingNodeExcuteResult.buildUnsatisfiedResult();
    }
}
