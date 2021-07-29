package net.seleniummc.mcchet.utils;

import com.slack.api.Slack;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;


public class SlackUtil {
    public static App app = new App();
    public static Slack slack;

    public static void main(String[] args){
        return;
    }

    public static void initSlack() throws Exception {
        System.out.println("slecc init");
        slack = Slack.getInstance();

        app.command("/list", (req, ctx) -> {
            return ctx.ack("a");
        });




        SlackAppServer server = new SlackAppServer(app);
        server.start();
    }

    public static void sendMessage(String msg, String channel) {
        System.out.println("Slecc Chet");
        try {
            System.out.println("Trying Slecc Chet");
//            app.client().chatPostMessage(r ->
//                    r
//                            .channel(channel)
//                            .text(msg));
            String token = System.getenv("SLACK_TOKEN");
            MethodsClient  methodsClient = slack.methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel("CD1JSG9UK")
                    .text(msg)
                    .build();

            ChatPostMessageResponse response = methodsClient.chatPostMessage(request);

        } catch (Exception e) {
            System.out.println("Slecc Chet bad");
            e.printStackTrace();
        }
        //app.client().chatPostMessage(channel, msg);
    }
}
