package com.wms.common;

import lombok.Data;

@Data
public class Result {
private Integer code; //业务状态码 200成功
private String msg; //提示信息

private Long total;//总记录数

private Object data;//数据

    public  static Result fail(){
    return result(400,"失败",0L,null);
}

    public  static Result success(){
        return result(200,"成功",0L,null);
    }
    public  static Result success(Object data){
        return result(200,"成功",0L,data);
    }
    public  static Result success(Object data,Long total){
        return result(200,"成功",total,data);
    }

private static Result result(Integer code,String msg,Long total,Object data){
    Result res = new Result();
    res.setData(data);
    res.setMsg(msg);
    res.setCode(code);
    res.setTotal(total);

    return res;

}

}
