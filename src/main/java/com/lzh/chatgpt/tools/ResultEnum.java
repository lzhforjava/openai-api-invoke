package com.lzh.chatgpt.tools;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.tools
 * @Project：chat-gpt
 * @name：ResultEnum
 * @Date：2023/3/20 16:56
 * @Filename：ResultEnum
 */
public enum ResultEnum {
    SUCCESS(0,"成功"),
    FAILURE(-1,"失败");

    private Integer code;
    private String data;

    private ResultEnum(Integer code, String data) {
        this.code = code;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}

