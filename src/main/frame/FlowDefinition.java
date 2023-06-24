package main.frame;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;


@Slf4j
public abstract class FlowDefinition {

    protected static final String CAT_NAME = "EAT_FLOW";

    /**
     * 流程执行
     * @param context
     * @return
     */
    protected final EatingNodeExcuteResult run(BaseNodeContext context){
        // nodeDefinition返回的列表中，包含的是实例化的节点
        List<List<EatNode<? extends BaseNodeContext>>> eatNodes = nodeDefinition();

        // 依次执行每一层的节点
        for(List<EatNode<? extends BaseNodeContext>> innerNodes: eatNodes){
            // 需要做一层类型强转，把实例化的子类节点转用父类声明，避免报错
            List<EatNode> nodes=new ArrayList<>(innerNodes);
            // 依次执行每一层的所有节点，合并同一层的结果
            EatingNodeExcuteResult result = nodes.stream()
                    .map(levelNode -> {
                        try {
                            EatingNodeExcuteResult nodeResult = levelNode.excute(context);
                            return nodeResult;
                        } catch (Exception e) {
                            // 抛异常时也要返回异常的结果信息
                            return EatingNodeExcuteResult.buildExceptionResult(e);
                        }
                    })          //reduce有两个参数，第一个参数给出了合并结果的初始值，第二个参数给出合并流中数据的方法
                    .reduce(EatingNodeExcuteResult.buildOriginResult(), merge());
            log.info("---------------------本轮结束---------------------");
            // 如果在这一轮已经吃饱了，就不需要管下一轮，直接return
            if(Objects.equals(result.getNextStep(), NodeNextStep.SATISFIED)){
                return result;
            }
        }
        // 每一轮都吃完了，仍然没有吃饱
        return EatingNodeExcuteResult.buildUnsatisfiedResult();
    }

    protected final BinaryOperator<EatingNodeExcuteResult> merge(){
        return (resultFirst, resultSecond) -> {
            // 在合并结果时，只要在该层的某一个节点吃饱了，最终的合并结果就是吃饱了
            if (Objects.equals(resultFirst.getNextStep(), NodeNextStep.SATISFIED) ||
                    Objects.equals(resultSecond.getNextStep(), NodeNextStep.SATISFIED)){
                return EatingNodeExcuteResult.buildSatisfiedResult();
            }

            // 走到这里说明还没吃饱
            return EatingNodeExcuteResult.buildUnsatisfiedResult();
        };
    }


    /**
     * 定义节点
     * 比如该餐厅一共有三轮上菜，第一轮两道AB，第二轮一道C，第三轮两道DE
     * 那么节点定义为
     * [
     *      [A,B]
     *      [C]
     *      [D,E]
     * ]
     * @return
     */
    protected abstract List<List<EatNode<? extends BaseNodeContext>>> nodeDefinition();
}
