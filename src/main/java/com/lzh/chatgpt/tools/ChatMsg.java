package com.lzh.chatgpt.tools;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.tools
 * @Project：chat-gpt
 * @name：ChatMsg
 * @Date：2023/3/20 16:55
 * @Filename：ChatMsg
 */
import java.util.List;

public class ChatMsg {
    //消息类型 1：聊天信息  2：刷新在线用户列表
    private String sendUserBh;
    private String sendUserXm;
    private String type;
    private String msg;
    private List<ChatUser> onlineUserList;

    public String getSendUserBh() {
        return sendUserBh;
    }

    public void setSendUserBh(String sendUserBh) {
        this.sendUserBh = sendUserBh;
    }

    public String getSendUserXm() {
        return sendUserXm;
    }

    public void setSendUserXm(String sendUserXm) {
        this.sendUserXm = sendUserXm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ChatUser> getOnlineUserList() {
        return onlineUserList;
    }

    public void setOnlineUserList(List<ChatUser> onlineUserList) {
        this.onlineUserList = onlineUserList;
    }
}
