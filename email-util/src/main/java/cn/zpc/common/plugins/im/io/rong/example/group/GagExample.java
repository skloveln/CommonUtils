package cn.zpc.common.plugins.im.io.rong.example.group;

import cn.zpc.common.plugins.im.io.rong.RongCloud;
import cn.zpc.common.plugins.im.io.rong.methods.group.gag.Gag;
import cn.zpc.common.plugins.im.io.rong.models.Result;
import cn.zpc.common.plugins.im.io.rong.models.group.GroupMember;
import cn.zpc.common.plugins.im.io.rong.models.group.GroupModel;
import cn.zpc.common.plugins.im.io.rong.models.response.ListGagGroupUserResult;
/**
 *
 * 群组禁言例子
 * @author hc
 * @date 2017/12/30
 * @version
 */
public class GagExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "appKey";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "appSercet";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.cn.ronghub.com";

    /**
     * 本地调用测试
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);

        Gag Gag = rongCloud.group.gag;
        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/gag.html#add
         * 添加禁言群成员方法
         */

        GroupMember[] members = {new GroupMember().setId("ghJiu7H1"),new GroupMember().setId("ghJiu7H2")};

        GroupModel group = new GroupModel()
                .setId("12")
                .setMembers(members)
                .setMunite(5);
        Result result = Gag.add(group);
        System.out.println("group.gag.add:  " + result.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/gag.html#getList
         * 查询被禁言群成员
         */
        ListGagGroupUserResult groupLisGagUserResult = Gag.getList("12");
        System.out.println("group.gag.getList:  " + groupLisGagUserResult.toString());

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/group/gag.html#remove
         * 移除禁言群成员
         */
        group = new GroupModel()
                .setId("IXQhMs3ny")
                .setMembers(members);

        //Result groupRollBackGagUserResult = Gag.remove(group);
        //System.out.println("group.gag.remove:  " + groupRollBackGagUserResult.toString());

    }
}
