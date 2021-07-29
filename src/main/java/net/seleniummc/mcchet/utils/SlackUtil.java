package net.seleniummc.mcchet.utils;

import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;
import com.slack.api.methods.SlackApiException;

import java.io.IOException;

public class SlackUtil {
    public static App app = new App();

    public static void main(String[] args){
        return;
    }

    public static void initSlack() throws Exception {
        System.out.println("slecc init");
        app.command("/list", (req, ctx) -> {
            return ctx.ack("a");
        });

        SlackAppServer server = new SlackAppServer(app);
        server.start();
    }

    public static void sendMessage(String msg, String channel) throws SlackApiException, IOException {
        System.out.println("Slecc Chet");
//        app.client().chatPostMessage(r ->
//                r
//                        .channel(channel)
//                        .text(msg));
        app.client().chatPostMessage(channel, msg);
    }
}
