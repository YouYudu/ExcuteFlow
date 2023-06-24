package main;


import lombok.extern.slf4j.Slf4j;
import main.eatflow.EatFlowFactory;
import main.idl.EatRequest;
import main.idl.EatResultInfo;
@Slf4j
public class myDinner {
    public static void main(String[] args) {
        EatFlowFactory eatFlowFactory=new EatFlowFactory();
        EatRequest request=new EatRequest(13, "雍和会");
        EatResultInfo resultInfo;
        try {
            resultInfo=eatFlowFactory.getEatFlow(request).eat(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("让我看看吃了哪些东西：\n"+resultInfo.toString());
    }
}