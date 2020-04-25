package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    private boolean success;//是否请求成功
    private String code;//消息码
    private String message;//错误信息
    private Object data;//返回数据
}
