package main.idl;

import java.util.List;

/**
 * 定义了请求结果的返回信息
 */
public class EatResultInfo {
    private List<String> itemList;

    @Override
    public String toString() {
        return "EatResultInfo{" +
                "itemList=" + itemList +
                '}';
    }

    public EatResultInfo(List<String> itemList) {
        this.itemList = itemList;
    }
}
