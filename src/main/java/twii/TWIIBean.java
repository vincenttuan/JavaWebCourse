package twii;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.stream.Stream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TWIIBean {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        //csv();
        json();
    }

    public static void json() throws IOException{
        String url = "http://localhost:8080/JavaWebCourse/files/BWIBBU_d.json";
        TWIIBean twiib = new TWIIBean();
        String jsonLine = twiib.run(url);
        System.out.println(jsonLine);
        GsonBuilder builder = new GsonBuilder();
        JsonElement jelement = new JsonParser().parse(jsonLine);
        //JsonObject jobject = jelement.getAsJsonObject();
        System.out.println(jelement.getAsJsonObject().getAsJsonArray("data"));
        
    }

    public static void csv() throws IOException {
        String url = "http://localhost:8080/JavaWebCourse/files/BWIBBU_d.csv";
        TWIIBean twiib = new TWIIBean();
        String[] data = twiib.run(url).split("\n");
        System.out.println(data.length);
        double yield = 7;
        double pe = 5;
        double pb = 1;
        Stream.of(data)
                .map(n -> n.replace("\"", ""))
                .map(n -> n.replace("-", "-1"))
                .map(n -> n.split(","))
                .filter(n -> n.length == 8 && !n[0].equals("證券代號"))
                .filter(n -> Double.parseDouble(n[2]) >= yield)
                .filter(n -> Double.parseDouble(n[4]) < pe)
                .filter(n -> Double.parseDouble(n[5]) < pb)
                .forEach(n -> System.out.println(n[0] + " " + n[1] + " " + n[2] + " " + n[3] + " " + n[4] + " " + n[5] + " " + n[7]));

    }
}
