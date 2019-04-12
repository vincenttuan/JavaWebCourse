package myjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JPA_Console {

    private static EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

    public static void main(String[] args) {

//        create(new User("Vincent", 30));
//        create(new User("Anita", 20));
//        create(new User("John", 10));
//        System.out.println(getUser(1L));
//        System.out.println(queryUsers());
//        System.out.println(query());
//        update();
        delete();
        JPAUtil.shutdown();
    }

    public static void create(User user) {

        // 進入交易模式
        EntityTransaction etx = em.getTransaction();
        // 交易開始
        etx.begin();
        // 交易內容
        em.persist(user);
        // 交易確認
        etx.commit();

        System.out.println("1.新增完畢成功");
        //em.close();
    }

    public static User getUser(long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public static List queryUsers() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("FROM User user", User.class);
        List list = query.getResultList();
        em.close();
        return list;
    }

    public static List query() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createNativeQuery("Select id, name, age from APP.T_USER", User.class);
        List list = query.getResultList();
        em.close();
        return list;
    }

    public static void update() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        User user = em.find(User.class, 4L);
        System.out.println(user);
        if (user != null) {
            user.setName("Tom");
            user.setAge(10);
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            em.persist(user); // em.merge(user); 看Entity 的狀態為何
            etx.commit();
            System.out.println("2.修改成功");
            System.out.println(user);
        }
        em.close();

    }

    public static void delete() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        User user = em.find(User.class, 6L);
        System.out.println(user);
        if (user != null) {
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            em.remove(user); // em.remove(em.merge(user)); 看Entity 的狀態為何
            etx.commit();
            System.out.println("3.刪除成功");
        }
        em.close();

    }
}
