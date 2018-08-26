package cn.zpc.common.plugins.im.io.rong.example.message;

import cn.zpc.common.plugins.im.io.rong.RongCloud;
import cn.zpc.common.plugins.im.io.rong.messages.CustomTxtMessage;
import cn.zpc.common.plugins.im.io.rong.messages.TxtMessage;
import cn.zpc.common.plugins.im.io.rong.messages.VoiceMessage;
import cn.zpc.common.plugins.im.io.rong.methods.message._private.Private;
import cn.zpc.common.plugins.im.io.rong.methods.message.chatroom.Chatroom;
import cn.zpc.common.plugins.im.io.rong.methods.message.discussion.Discussion;
import cn.zpc.common.plugins.im.io.rong.methods.message.group.Group;
import cn.zpc.common.plugins.im.io.rong.methods.message.history.History;
import cn.zpc.common.plugins.im.io.rong.methods.message.system.MsgSystem;
import cn.zpc.common.plugins.im.io.rong.models.message.*;
import cn.zpc.common.plugins.im.io.rong.models.response.HistoryMessageResult;
import cn.zpc.common.plugins.im.io.rong.models.response.ResponseResult;
import cn.zpc.common.plugins.im.io.rong.util.GsonUtil;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * 消息发送示例
 * @date 2018-03-09
 * @author hc
 * @version 2.0.0
 */
public class MessageExample {
    private static final String JSONFILE = MessageExample.class.getClassLoader().getResource("jsonsource").getPath()+"/";
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSecret";

    private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
    private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.cn.ronghub.com";

    public static void main(String[] args) throws Exception {

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        //RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Private Private = rongCloud.message.msgPrivate;
        MsgSystem system = rongCloud.message.system;
        Group group = rongCloud.message.group;
        Chatroom chatroom = rongCloud.message.chatroom;
        Discussion discussion = rongCloud.message.discussion;
        History history = rongCloud.message.history;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/system.html#send
         *
         * 发送系统消息
         *
         */
        String[] targetIds = {"2651280140445094444"};
        SystemMessage systemMessage = new SystemMessage()
                .setSenderUserId("usetId")
                .setTargetId(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{'pushData':'hello'}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setContentAvailable(0);

        ResponseResult result = system.send(systemMessage);
        System.out.println("send system message:  " + result.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/system.html#sendTemplate
         *
         * 发送系统模板消息方法
         *
         */
        Reader reader = null ;
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"/message/"+"TemplateMessage.json"));
            TemplateMessage template = (TemplateMessage)GsonUtil.fromJson(reader, TemplateMessage.class);
            ResponseResult systemTemplateResult = system.sendTemplate(template);
            System.out.println("send system template message:  " + systemTemplateResult.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/system.html#sendTemplate
         *
         * 发送系统模板消息方法
         *
         */
        BroadcastMessage message = new BroadcastMessage()
                .setSenderUserId("Hji8yh76")
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{'pushData':'hello'}")
                .setOs("iOS");
        ResponseResult broadcastResult = rongCloud.message.system.broadcast(message);
        System.out.println("send broadcast:  " + broadcastResult.toString());


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/private.html#send
         *
         * 发送单聊消息
         * */
        PrivateMessage privateMessage = new PrivateMessage()
                .setSenderUserId("2609751433442958892")
                .setTargetId(targetIds)
                .setObjectName(voiceMessage.getType())
                .setContent(voiceMessage)
                .setPushContent("")
                .setPushData("{\"pushData\":\"hello\"}")
                .setCount("4")
                .setVerifyBlacklist(0)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0);
        ResponseResult privateResult = Private.send(privateMessage);
        System.out.println("send private message:  " + privateResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/private.html#sendTemplate
         *
         * 发送单聊模板消息方法
         */
        try {
            reader = new InputStreamReader(new FileInputStream(JSONFILE+"/message/"+"TemplateMessage.json"));
            TemplateMessage template  =  (TemplateMessage) GsonUtil.fromJson(reader, TemplateMessage.class);
            ResponseResult messagePublishTemplateResult = Private.sendTemplate(template);

            System.out.println("send privateTemplate message:  " + messagePublishTemplateResult.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != reader){
                reader.close();
            }
        }
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/private.html#recall
         *
         * 撤回单聊消息
         * */
        RecallMessage recallMessage = new RecallMessage()
                .setSenderUserId("2609751433442958892")
                .setTargetId("2651280140445094444")
                .setuId("5H6P-CGC6-44QR-VB3R")
                .setSentTime("1519444243981");
        ResponseResult recallPrivateResult = (ResponseResult)Private.recall(recallMessage);
        System.out.println("recall private:  " + recallPrivateResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/group.html#send
         *
         * 群组消息
         * */
        GroupMessage groupMessage = new GroupMessage()
                .setSenderUserId("userId")
                .setTargetId(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);
       ResponseResult groupResult = group.send(groupMessage);

       System.out.println("send Group message:  " + groupResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/group.html#recall
         *
         * 群组撤回消息
         * */
        recallMessage = new RecallMessage()
                .setSenderUserId("sea9901")
                .setTargetId("markoiwm")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1522242030641");
        ResponseResult recallMessageResult = (ResponseResult)group.recall(recallMessage);

        System.out.println("send recall group message:  " + recallMessageResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/group.html#sendMention
         *
         * 群组@消息
         * */
        //要@的人
        String[] mentionIds = {"Hji8yh76","sea9901"};

        MentionedInfo mentionedInfo = new MentionedInfo(1,mentionIds,"");
        //@内容
        MentionMessageContent content = new MentionMessageContent(txtMessage,mentionedInfo);

        MentionMessage mentionMessage = new MentionMessage()
                .setSenderUserId("userId")
                .setTargetId(targetIds)
                .setObjectName(txtMessage.getType())
                .setContent(content)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);
        ResponseResult mentionResult = rongCloud.message.group.sendMention(mentionMessage);

        System.out.println("group mention result:  " + mentionResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/discussion.html#send
         *
         * 发送讨论组消息
         * */
        String[] discussionIds = {"lijhGk87","lijhGk88"};
        DiscussionMessage discussionMessage = new DiscussionMessage()
                .setSenderUserId("JuikH78ko")
                .setTargetId(discussionIds)
                .setObjectName(txtMessage.getType())
                .setContent(txtMessage)
                .setPushContent("this is a push")
                .setPushData("{\"pushData\":\"hello\"}")
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0)
                .setContentAvailable(0);

        ResponseResult discussionResult = discussion.send(discussionMessage);

        System.out.println("send discussion message:  " + discussionResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/discussion.html#recall
         *
         * 撤回讨论组消息
         * */
        recallMessage = new RecallMessage()
                .setSenderUserId("sea9901")
                .setTargetId("IXQhMs3ny")
                .setuId("5GSB-RPM1-KP8H-9JHF")
                .setSentTime("1519444243981");
        ResponseResult recallDiscussionResult = (ResponseResult)discussion.recall(recallMessage);

        System.out.println("recall discussion message:  " + recallDiscussionResult.toString());


        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/chatroom.html#send
         *
         * 聊天室消息
         * */

        String[] chatroomIds = {"15222258878654823358"};

        CustomTxtMessage ctm = new CustomTxtMessage("hello");
        ChatroomMessage chatroomMessage = new ChatroomMessage()
                .setSenderUserId("1")
                .setTargetId(chatroomIds)
                .setContent(ctm)
                .setObjectName(ctm.getType());

        ResponseResult chatroomResult = chatroom.send(chatroomMessage);
        System.out.println("send chatroom message:  " + chatroomResult.toString());
        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/chatroom.html#broadcast
         *
         * 聊天室广播消息
         *
         * 此功能需开通专有服务: http://www.rongcloud.cn/deployment#overseas-cloud
         *
         * */
        chatroomMessage = new ChatroomMessage()
                .setSenderUserId("bN6oQi8T5")
                .setContent(txtMessage)
                .setObjectName(txtMessage.getType());

        ResponseResult chatroomBroadcastresult = chatroom.broadcast(chatroomMessage);
        System.out.println("send chatroom broadcast message:  " + chatroomBroadcastresult.toString());


        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/history.html#get
         *
         * 获取历史消息日志文件
         *
         * */

        HistoryMessageResult historyMessageResult = history.get("2018032810");
        System.out.println("get history  message:  " + historyMessageResult.toString());

        /**
         *
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/message/history.html#get
         *
         * 删除历史消息日志文件
         *
         * */

        ResponseResult removeHistoryMessageResult = history.remove("2018030210");
        System.out.println("remove history  message:  " + removeHistoryMessageResult.toString());


    }
}
