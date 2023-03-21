package com.lzh.chatgpt.tools;


import com.alibaba.fastjson.JSON;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author：LZH
 * @Package：com.lzh.chatgpt.tools
 * @Project：chat-gpt
 * @name：WebSocketTool
 * @Date：2023/3/20 16:54
 * @Filename：WebSocketTool
 */
public class WsTool {
    public static final Map<String, Session> LIVING_SESSIONS_CACHE = new ConcurrentHashMap<>();
    public static final Map<String, ChatUser> LIVING_USER_CACHE = new ConcurrentHashMap<>();

    public static void sendMessageAll(ChatMsg chatMsg) {
        LIVING_SESSIONS_CACHE.forEach((sessionId, session) -> {
            sendMessage(session, chatMsg);
        });
    }

    /**
     * 发送给指定用户消息
     *
     * @param session 用户 session
     * @param chatMsg 发送内容
     */
    public static void sendMessage(Session session, ChatMsg chatMsg) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(JSON.toJSONString(chatMsg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 刷新在线用户
     */
    public static void refreshOnlineUserList() {
        ChatMsg chatMsg = new ChatMsg();
        chatMsg.setType(ChatEnum.USER_LIST.getCode());
        List<ChatUser> userList = new ArrayList<ChatUser>(WsTool.LIVING_USER_CACHE.values());
        chatMsg.setOnlineUserList(userList);
        sendMessageAll(chatMsg);
    }
}

