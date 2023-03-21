//package com.lzh.chatgpt.controller;
//
//
//import com.lzh.chatgpt.tools.ChatEnum;
//import com.lzh.chatgpt.tools.ChatMsg;
//import com.lzh.chatgpt.tools.ChatUser;
//import com.lzh.chatgpt.tools.Result;
//import com.lzh.chatgpt.tools.ResultEnum;
//import com.lzh.chatgpt.tools.WsTool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @Author：LZH
// * @Package：com.lzh.chatgpt.controller
// * @Project：chat-gpt
// * @name：ChatroomController
// * @Date：2023/3/20 16:57
// * @Filename：ChatroomController
// */
////在线聊天室
//@RestController
//@ServerEndpoint("/connect/{userBh}/{userName}")
//public class ChatroomController {
//    private static final Logger log = LoggerFactory.getLogger(ChatroomController.class);
//
//    @OnOpen
//    public void openSession(@PathParam("userBh")String userBh, @PathParam("userName")String userName, Session session) {
//        SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        ChatUser chatUser = new ChatUser();
//        chatUser.setUserBh(userBh);
//        chatUser.setUserName(userName);
//        chatUser.setOnlineTime(sdf.format(new Date()));
//
//        WsTool.LIVING_SESSIONS_CACHE.put(userBh, session);
//        WsTool.LIVING_USER_CACHE.put(userBh,chatUser);
//        ChatMsg chatMsg = new ChatMsg();
//        chatMsg.setSendUserBh(chatUser.getUserBh());
//        chatMsg.setSendUserXm(chatUser.getUserName());
//        chatMsg.setType(ChatEnum.JOIN_CHAT.getCode());
//        WsTool.sendMessageAll(chatMsg);
//
//        //刷新用户列表
//        WsTool.refreshOnlineUserList();
//    }
//
//    @OnMessage
//    public void onMessage(@PathParam("userBh") String userBh, String message) {
//        log.info(message);
//        //心跳程序
//        if("HeartBeat".equals(message)){
//            return;
//        }
//        ChatUser chatUser = WsTool.LIVING_USER_CACHE.get(userBh);
//        ChatMsg chatMsg = new ChatMsg();
//        chatMsg.setSendUserBh(chatUser.getUserBh());
//        chatMsg.setSendUserXm(chatUser.getUserName());
//        chatMsg.setType(ChatEnum.PUBLIC_MSG.getCode());
//        chatMsg.setMsg(message);
//        WsTool.sendMessageAll(chatMsg);
//    }
//
//    @OnClose
//    public void onClose(@PathParam("userBh")String userBh, Session session) {
//        ChatUser chatUser = WsTool.LIVING_USER_CACHE.get(userBh);
//        //当前的Session 移除
//        WsTool.LIVING_SESSIONS_CACHE.remove(chatUser.getUserBh());
//        WsTool.LIVING_USER_CACHE.remove(chatUser.getUserBh());
//        //并且通知其他人当前用户已经离开聊天室了
//        ChatMsg chatMsg = new ChatMsg();
//        chatMsg.setSendUserBh(chatUser.getUserBh());
//        chatMsg.setSendUserXm(chatUser.getUserName());
//        chatMsg.setType(ChatEnum.CLOSE_SOCKET.getCode());
//        WsTool.sendMessageAll(chatMsg);
//        //刷新用户列表
//        WsTool.refreshOnlineUserList();
//        try {
//            session.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @OnError
//    public void onError(Session session, Throwable throwable) {
//        try {
//            session.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        throwable.printStackTrace();
//    }
//
//    //一对一私聊
//    @GetMapping("/privateSend/{sendUserBh}/to/{receiveUserBh}")
//    public Result privateSend(@PathVariable("sendUserBh") String sendUserBh, @PathVariable("receiveUserBh") String receiveUserBh, String message) {
//        Session sendSession = WsTool.LIVING_SESSIONS_CACHE.get(sendUserBh);
//        Session receiveSession = WsTool.LIVING_SESSIONS_CACHE.get(receiveUserBh);
//        ChatUser sendUser = WsTool.LIVING_USER_CACHE.get(sendUserBh);
//        ChatUser receiver = WsTool.LIVING_USER_CACHE.get(receiveUserBh);
//        ChatMsg chatMsg = new ChatMsg();
//        chatMsg.setSendUserBh(sendUser.getUserBh());
//        chatMsg.setSendUserXm(sendUser.getUserName());
//
//        chatMsg.setType(ChatEnum.PRIVATE_MSG.getCode());
//        chatMsg.setMsg(message);
//        //对发送人发送
//        WsTool.sendMessage(sendSession,  chatMsg);
//
//        //接受人发送
//        WsTool.sendMessage(receiveSession,  chatMsg);
//        Result result = new Result();
//        result.setCode(ResultEnum.SUCCESS.getCode());
//        return result;
//    }
//}
//
