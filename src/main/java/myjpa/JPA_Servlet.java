package myjpa;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/myjpa/JPA_Servlet")
public class JPA_Servlet extends HttpServlet {
    private  EntityManager em = 
            JPAUtil.getEntityManagerFactory().createEntityManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.print(queryAll());
    }
    
    private void save(User user, PrintWriter out) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.persist(user);
        etx.commit();
    }
    
    private void update(User user, PrintWriter out) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.merge(user);
        etx.commit();
    }
    
    private void delete(User user, PrintWriter out) {
        EntityTransaction etx = em.getTransaction();
        etx.begin();
        em.remove(em.merge(user));
        etx.commit();
    }
    
    private User findById(Long id) {
        User user = em.find(User.class, id);
        return user;
    }
    
    private List queryByAge(int age) {
        Query query = em.createQuery("SELECT user FROM User user WHERE user.age = :age");
        query.setParameter("age", age);
        List list = query.getResultList();
        return list;
    }
    
    private List queryAll() {
        Query query = em.createNativeQuery("Select id, name, age from APP.T_USER", User.class);
        List list = query.getResultList();
        return list;
    }
}
