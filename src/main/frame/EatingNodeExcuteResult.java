package main.frame;

import lombok.Data;

/**
 * 定义了某个节点的执行结果
 */
@Data
public class EatingNodeExcuteResult {
    /**
     * 节点执行后流转
     */
    private NodeNextStep nextStep;

    /**
     * 吃的深度
     */
    private int eatingDepth;

    /**
     * 是否出现异常
     */
    private boolean hasException;

    /**
     * 异常
     */
    private Exception exception;

    /**
     * 初始化时，默认的结果信息
     * @return
     */
    public static EatingNodeExcuteResult buildOriginResult(){
        EatingNodeExcuteResult res=new EatingNodeExcuteResult();
        res.setNextStep(NodeNextStep.NO_OP);
        return res;
    }

    /**
     * 没吃饱
     * @return
     */
    public static EatingNodeExcuteResult buildUnsatisfiedResult(){
        EatingNodeExcuteResult res=new EatingNodeExcuteResult();
        res.setNextStep(NodeNextStep.UNSATISFIED);
        return res;
    }

    /**
     * 已经吃饱了
     * @return
     */
    public static EatingNodeExcuteResult buildSatisfiedResult(){
        EatingNodeExcuteResult res=new EatingNodeExcuteResult();
        res.setNextStep(NodeNextStep.SATISFIED);
        return res;
    }

    /**
     * 构造异常结果
     * @param e
     * @return
     */
    public static EatingNodeExcuteResult buildExceptionResult(Exception e){
        EatingNodeExcuteResult res=new EatingNodeExcuteResult();
        res.setNextStep(NodeNextStep.SATISFIED);
        res.setHasException(true);
        res.setException(e);
        return res;
    }
}
