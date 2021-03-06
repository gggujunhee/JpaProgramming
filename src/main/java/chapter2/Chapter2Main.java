package chapter2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Chapter2Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaprogramming");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            logic(em);
            tx.commit();
        } catch (Exception e) {
            System.out.println("error! : " + e.toString());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUserName("junhee");
        member.setAge(2);

        em.persist(member);

        member.setAge(28);

        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUserName() + ", age=" + findMember.getAge());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();

        System.out.println("members.size=" + members.size());
        System.out.println(members);

        em.remove(findMember);
    }
}
