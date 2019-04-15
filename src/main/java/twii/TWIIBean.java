package twii;

import java.io.IOException;
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
        String url = "http://www.twse.com.tw/exchangeReport/BWIBBU_d?response=csv&date=20190415&selectType=ALL";
        TWIIBean twiib = new TWIIBean();
        String data = twiib.run(url);
        System.out.println(data);
    }
}
