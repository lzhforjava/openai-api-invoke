package com.lzh.chatgpt.tools;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.tools
 * @Project：chat-gpt
 * @name：ChatUser
 * @Date：2023/3/20 16:55
 * @Filename：ChatUser
 */
public class ChatUser {
    private String userBh;
    private String userName;
    private String onlineTime;

    public String getUserBh() {
        return userBh;
    }

    public void setUserBh(String userBh) {
        this.userBh = userBh;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(String onlineTime) {
        this.onlineTime = onlineTime;
    }
}

