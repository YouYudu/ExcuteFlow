package main.frame;

import lombok.Getter;

@Getter
public enum NodeNextStep {
    SATISFIED(1, "吃饱了，不继续吃了"),
    UNSATISFIED(2, "没吃饱，还要继续吃下一轮的菜"),
    NO_OP(3, "无操作"); // 初始化、抛异常时使用

    private Integer code;
    private String desc;

    NodeNextStep(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }

}
