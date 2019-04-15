package twii;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
        List<TWII> list = toTWIIList(csv());
        System.out.println(list);
    }

    

    public static List csv() throws IOException {
        String url = "http://localhost:8080/JavaWebCourse/files/BWIBBU_d.csv";
        TWIIBean twiib = new TWIIBean();
        String[] data = twiib.run(url).split("\n");
        System.out.println(data.length);
        List list = Stream.of(data)
                .map(n -> n.replace("\"", ""))
                .map(n -> n.replace("-", "-1"))
                .map(n -> n.split(","))
                .filter(n -> n.length == 8 && !n[0].equals("證券代號"))
                .collect(Collectors.toCollection(ArrayList::new));
        return list;
    }
    
    private static List<TWII> toTWIIList(List list) {
        List<TWII> twiiList = new ArrayList<>();
        for(Object data : list) {
            String[] arr = ((String[])data);
            TWII twii = new TWII();
            twii.setStockNo(arr[0]);
            twii.setStockName(arr[1]);
            twii.setYield(Double.parseDouble(arr[2]));
            twii.setPe(Double.parseDouble(arr[4]));
            twii.setPb(Double.parseDouble(arr[5]));
            twiiList.add(twii);
        }
        return twiiList;
    }
    
    public static void json() throws IOException{
        String url = "http://localhost:8080/JavaWebCourse/files/BWIBBU_d.json";
        TWIIBean twiib = new TWIIBean();
        String jsonLine = twiib.run(url);
        //System.out.println(jsonLine);
        GsonBuilder builder = new GsonBuilder();
        JsonElement jelement = new JsonParser().parse(jsonLine);
        Stream.of(jelement.getAsJsonObject().getAsJsonArray("data"))
                .forEach(System.out::println);
        // homework
    }
}
