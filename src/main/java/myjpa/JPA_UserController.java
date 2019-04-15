package myjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JPA_UserController {
    private  EntityManager em = 
            JPAUtil.getEntityManagerFactory().createEntityManager();
    
    public void save(User user) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(user);
        etx.commit();
    }
    
    public void update(User user) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.merge(user);
        etx.commit();
    }
    
    public void delete(User user) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.remove(em.merge(user));
        etx.commit();
    }
    
    public User findById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }
    
    public List queryByAge(int age) {
        Query query = em.createQuery("SELECT user FROM User user WHERE user.age = :age");
        query.setParameter("age", age);
        List list = query.getResultList();
        return list;
    }
    
    public List queryAll() {
        Query query = em.createNativeQuery("Select id, name, age from T_USER", User.class);
        List list = query.getResultList();
        return list;
    }
}
