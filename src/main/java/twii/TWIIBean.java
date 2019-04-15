package twii;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import myjpa.JPAUtil;
import myjpa.User;
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
    
    private static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
    
    public static void main(String[] args) throws IOException {
        List<TWII> list = toTWIIList_java8(csv());
        //System.out.println(list);
        //create(list);
        List qlist = query(10.0, 5, 1);
        System.out.println(qlist.size() + "筆");
        System.out.println(qlist);
    }
    
    // 匯入
    public static void create(List<TWII> list) {

        // 進入交易模式
        EntityTransaction etx = em.getTransaction();
        // 交易開始
        etx.begin();
        // 交易內容
        em.createNativeQuery("DELETE From APP.T_TWII").executeUpdate();
        for(TWII twii : list) {
            em.persist(twii);
        }
        // 交易確認
        etx.commit();

        System.out.println("1.新增完畢成功");
        //em.close();
    }
    
    // 查詢
    public static List query(double yield, double pe, double pb) {
        Query query = em.createQuery("SELECT twii FROM TWII twii WHERE twii.yield > :yield AND twii.pe < :pe AND twii.pb < :pb");
        query.setParameter("yield", yield);
        query.setParameter("pe", pe);
        query.setParameter("pb", pb);
        List list = query.getResultList();
        return list;
    }
    
    // csv 轉 List
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
    
    // List<?> 轉 List<TWII>
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
    
    // List<?> 轉 List<TWII> for java 8
    private static List<TWII> toTWIIList_java8(List list) {
        List twiiList = new ArrayList<>();
        list.stream().map((data) -> {
            String[] arr = ((String[])data);
            TWII twii = new TWII();
            twii.setStockNo(arr[0]);
            twii.setStockName(arr[1]);
            twii.setYield(Double.parseDouble(arr[2]));
            twii.setPe(Double.parseDouble(arr[4]));
            twii.setPb(Double.parseDouble(arr[5]));
            return twii;
        }).forEachOrdered((twii) -> {
            twiiList.add(twii);
        });
        return twiiList;
    }
    
    // for json (homework)
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
