package net.seleniummc.mcchet.utils;

import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;
import com.slack.api.model.event.MessageEvent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SlackUtil {
    public static App app = new App();
    private static String notificationChannelId = getSlackChannel();

    public static void main(String[] args) {
        return;
    }
    public static void initSlack() throws Exception {
        app.command("/list", (req, ctx) -> {
            return ctx.ack("a");
        });

        Pattern sdk = Pattern.compile(".*");
        app.message(sdk, (payload, ctx) -> {
          MessageEvent event = payload.getEvent();
          String text = event.getText();
          String channelId = event.getChannel();
          String mainChannel = "C02AAFY8872";
          if(!channelId.equals(mainChannel)){
            return ctx.ack();
          }
          String userId = event.getUser();
          UsersProfileGetResponse result =  ctx.client().usersProfileGet(r ->
                  r
                          .token(ctx.getBotToken())
                          .user(userId)
          );
          String displayName = result.getProfile().getDisplayName();

          // the variables u need are `text` and `displayName`. send them to minecraft here
          System.out.println(text);
          System.out.println(displayName);
          return ctx.ack();
        });

        SlackAppServer server = new SlackAppServer(app);
        server.start();
    }

    public static String getSlackChannel() {
        // TODO: get this from config/MCChet.cfg
        return "C02AAFY8872";
    }

    public static String getBotToken() {
        // TODO: get this from config/MCChet.cfg
        return "nothing";
    }

    public static void sendMessage(String msg, String iconURL, String username) throws IOException {
        String channel = getSlackChannel();
        System.out.println("Slecc Chet");
        URL url = new URL("https://slack.com/api/chat.postMessage");
        URLConnection con = url.openConnection();
        HttpURLConnection http = (HttpURLConnection) con;
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        String body = "{" +
                "\"channel\": \"" + channel + "\"," +
                "\"text\": \"" + msg + "\"," +
                "\"icon_url\": \"" + iconURL + "\"," +
                "\"username\": \"" + username + "\"" +
                "}";
        byte[] out = body.getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        http.setFixedLengthStreamingMode(length);
        http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        http.setRequestProperty("Authorization", "Bearer " + getBotToken());
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        InputStream inputStream = http.getInputStream();
        String text = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        System.out.println(text);
    }



    public static String getPlayerAvatarLink(String uuid) {
        return "http://cravatar.eu/avatar/" + uuid;
    }
}
