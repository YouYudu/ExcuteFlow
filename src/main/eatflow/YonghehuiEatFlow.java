package main.eatflow;


import com.sun.javafx.collections.ImmutableObservableList;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.extern.slf4j.Slf4j;
import main.eatflow.context.EatNormalContext;
import main.eatflow.nodes.*;
import main.frame.BaseNodeContext;
import main.frame.EatNode;
import main.frame.EatingNodeExcuteResult;
import main.idl.EatRequest;
import main.idl.EatResultInfo;
import org.apache.flink.shaded.guava18.com.google.common.collect.ImmutableList;

import java.util.List;

@Slf4j
public class YonghehuiEatFlow extends EatFlow{
    private String name="雍和会";
    private AppleNode appleNode;
    private BananaNode bananaNode;
    private KebabNode kebabNode;
    private IceCreamNode iceCreamNode;
    private PuddingNode puddingNode;

    public YonghehuiEatFlow() {
        appleNode=new AppleNode();
        bananaNode=new BananaNode();
        kebabNode=new KebabNode();
        iceCreamNode=new IceCreamNode();
        puddingNode=new PuddingNode();
    }

    public String getName() {
        return name;
    }

    /**
     * 把请求转化成上下文信息
     *
     * @param request
     */
    @Override
    protected BaseNodeContext convertContext(EatRequest request) throws Exception {
        EatNormalContext context=new EatNormalContext(request.getGastricCapacity());
        return context;
    }

    /**
     * 把上下文转化为返回值
     *
     * @param context
     */
    @Override
    protected EatResultInfo convertResult(BaseNodeContext context) throws Exception {
        EatResultInfo info=new EatResultInfo(context.getAlreadyEatItems());
        return info;
    }

    /**
     * 定义节点
     * 比如该餐厅一共有三轮上菜，第一轮两道AB，第二轮一道C，第三轮两道DE
     * 那么节点定义为
     * [
     * [A,B]
     * [C]
     * [D,E]
     * ]
     *
     * @return
     */
    @Override
    protected List<List<EatNode<? extends BaseNodeContext>>> nodeDefinition() {
        return ImmutableList.of(
                ImmutableList.of(appleNode, bananaNode),
                ImmutableList.of(kebabNode),
                ImmutableList.of(iceCreamNode, puddingNode)
        );
    }
}
