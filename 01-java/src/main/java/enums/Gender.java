package enums;

public enum Gender {//Enum是一个特殊的数据类型
    MALE("男士"),FEMALE("女士"),NONE("没有要求");//三个枚举实例
    private String name;

    Gender(String name){
       this.name=name;
    }
    public String getName() {
        return name;
    }
}
