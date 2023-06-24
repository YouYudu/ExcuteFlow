package main.frame;


/**
 * 吃东西节点
 * excute在执行时需要的上下文的类型通过模板指定--上下文继承自BaseNodeContext
 * @param
 */
public interface EatNode<CONTEXT extends BaseNodeContext> {
    /**
     * 执行节点逻辑
     * @param context
     * @return
     * @throws Exception
     */
    EatingNodeExcuteResult excute(CONTEXT context) throws Exception;
}
