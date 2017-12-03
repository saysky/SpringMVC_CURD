package com.liuyanzhao.blog.enums;

/**
 * @author 言曌
 * @date 2017/11/30 下午11:37
 */

public enum ResultEnum {

    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数不正确"),

    USER_NOT_EXIST(10,"用户不存在"),
    USER_CREATE_FAIL(11,"用户创建失败"),
    USER_UPDATE_FAIL(12,"用户更新失败"),
    USER_DELETE_FAIL(13,"用户删除失败"),
    USERNAME_IS_EXIST(14,"用户名已存在")


    ;


    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
