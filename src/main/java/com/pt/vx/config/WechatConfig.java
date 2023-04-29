package com.pt.vx.config;

import com.pt.vx.pojo.BirthDay;
import com.pt.vx.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class WechatConfig {
    /**
     * 你的微信的APPID
     * appId
     */
    public static final String VxAppId = "wxf055612617259688";

    /**
     * 你的微信的密钥
     * appSecret
     */
    public static final String VxAppSecret = "9d6f67619e579a6aa055a1fc89f4ec8d";

    public static final List<User> userList = new ArrayList<>();

   package com.mycompany.notification;
import com.mycompany.notification.pojo.Birthday;
import com.mycompany.notification.pojo.User;
import com.mycompany.wechat.WeChatService;
import com.mycompany.wechat.pojo.TemplateData;
import com.mycompany.wechat.pojo.TemplateMessage;
import java.util.ArrayList;
import java.util.List;
public class NotificationSender {
    public static void main(String[] args) {
        // 获取通知模板的ID、标题、内容和发送者微信号
        String templateId = NotificationConfig.getTemplateId();
        String title = NotificationConfig.getTitle();
        String content = NotificationConfig.getContent();
        String senderVx = NotificationConfig.getSenderVx();
        // 获取通知接收者列表
        List<User> userList = NotificationConfig.getUserList();
        // 遍历通知接收者列表，向每个用户发送通知
        for (User user : userList) {
            // 获取用户的微信号、姓名和城市
            String userVx = user.getVx();
            String userName = user.getUserName();
            String city = user.getCity();
            // 获取用户的生日信息
            Birthday[] birthdays = user.getBirthdays();
            // 构造模板消息
            TemplateMessage templateMessage = new TemplateMessage();
            templateMessage.setTemplateId(templateId);
            templateMessage.setToUser(userVx);
            templateMessage.setUrl("");
            List<TemplateData> dataList = new ArrayList<>();
            // 设置模板消息的各个参数
            dataList.add(new TemplateData("first", title));
            dataList.add(new TemplateData("keyword1", userName));
            dataList.add(new TemplateData("keyword2", city));
            dataList.add(new TemplateData("keyword3", content.replace("{date}", "2022年10月1日").replace("{time}", "9:00-18:00")));
            dataList.add(new TemplateData("remark", "祝您生活愉快！"));
            templateMessage.setDataList(dataList);
            // 发送模板消息
            WeChatService.sendTemplateMessage(senderVx, templateMessage);
        }
    }
}
