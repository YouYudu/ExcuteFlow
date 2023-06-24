package main.eatflow;

import main.frame.BaseNodeContext;
import main.frame.EatingNodeExcuteResult;
import main.frame.FlowDefinition;
import main.idl.EatRequest;
import main.idl.EatResultInfo;

public abstract class EatFlow<CONTEXT extends BaseNodeContext> extends FlowDefinition {

    public final EatResultInfo eat(EatRequest request) throws Exception{
        CONTEXT context=convertContext(request);
        run(context);
        return convertResult(context);
    }

    /**
     * 把请求转化成上下文信息
     */
    protected abstract CONTEXT convertContext(EatRequest request) throws Exception;

    /**
     * 把上下文转化为返回值
     */
    protected abstract EatResultInfo convertResult(CONTEXT context) throws Exception;
}
