package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO {
    // 資料庫
    private static List<String> users;
    static {
        users = new ArrayList<>(
                Arrays.asList("javase", "python", "javaee", "swift")
        );
    }
    
    // 全部查詢
    public List<String> queryAll() {
        return users;
    }
    
    // 查詢單筆
    public String get(int id) {
        return users.get(id);
    }
    
    // 測試用(模擬 RestUser Servlet 的使用)
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        System.out.println(dao.queryAll());
        System.out.println(dao.get(2));
    }
    
}
