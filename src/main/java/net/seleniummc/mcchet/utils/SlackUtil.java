package net.seleniummc.mcchet.utils;

import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SlackUtil {
    public static App app = new App();
    public static void main(String[] args) {
        return;
    }
    public static void initSlack() throws Exception {
        app.command("/list", (req, ctx) -> {
            return ctx.ack("a");
        });
        SlackAppServer server = new SlackAppServer(app);
        server.start();
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
        http.setRequestProperty("Authorization", "Bearer " + "xoxb-2210535565-2306134277862-f5RvJ9mj1SAwpR9J5rFpM5w6");
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

    public static String getSlackChannel() {
        // TODO: get this from the config
        return "C02AAFY8872";
    }

    public static String getPlayerAvatarLink(String uuid) {
        return "http://cravatar.eu/avatar/" + uuid;
    }
}