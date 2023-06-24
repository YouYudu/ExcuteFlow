package main.idl;

public enum Restaurant {
    YHH(1, "雍和会");

    private Integer code;
    private String desc;

    Restaurant(Integer code, String desc){
        this.code=code;
        this.desc=desc;
    }
}
