package com.lzh.chatgpt.tools;


import java.io.Serializable;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.tools
 * @Project：chat-gpt
 * @name：Result
 * @Date：2023/3/20 16:56
 * @Filename：Result
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 3337439376898084639L;

    /**
     * 处理状态 0成功，-1 失败
     */
    private Integer code;

    /**
     * 处理信息
     */
    private String msg;

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

