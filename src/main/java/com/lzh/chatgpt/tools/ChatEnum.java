package com.lzh.chatgpt.tools;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.tools
 * @Project：chat-gpt
 * @name：ChatEnum
 * @Date：2023/3/20 16:56
 * @Filename：ChatEnum
 */
public enum ChatEnum {
    PUBLIC_MSG("1","公共聊天消息"),
    PRIVATE_MSG("2","私秘聊天信息"),
    CLOSE_SOCKET("3","关闭socket连接"),
    USER_LIST("4","在线用户列表"),
    JOIN_CHAT("5","加入聊天室");

    private String code;
    private String data;

    private ChatEnum(String code, String data) {
        this.code = code;
        this.data = data;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}

