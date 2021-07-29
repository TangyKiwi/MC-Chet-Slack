package net.seleniummc.mcchet.utils;
import com.slack.api.bolt.App;
import com.slack.api.bolt.jetty.SlackAppServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
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
    public static void sendMessage(String msg, String channel, String iconURL, String username) throws IOException {
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
        http.setRequestProperty("Authorization", "Bearer " + System.getenv("SLACK_BOT_TOKEN"));
        http.connect();
        try (OutputStream os = http.getOutputStream()) {
            os.write(out);
        }
        System.out.println(http.getResponseMessage());
    }
}