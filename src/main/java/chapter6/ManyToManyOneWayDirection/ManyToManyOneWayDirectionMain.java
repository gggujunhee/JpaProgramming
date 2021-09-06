package chapter6.ManyToManyOneWayDirection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ManyToManyOneWayDirectionMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaprogramming");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            //save(em);
            find(em);
            tx.commit();
        } catch (Exception e) {
            System.out.println("error! : " + e.toString());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void save(EntityManager em) {
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.getProducts().add(productA);
        em.persist(member1);
    }

    private static void find(EntityManager em) {
        save(em);
        em.flush();
        em.clear();

        Member member = em.find(Member.class, "member1");
        List<Product> products = member.getProducts();
        for (Product product : products) {
            System.out.println("product_name = " + product.getName());
        }
    }
}
