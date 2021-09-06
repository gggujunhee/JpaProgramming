package chapter6.oneToManyOneWayDirection;

import javax.persistence.*;

public class OneToManyOneWayDirectionMain {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaprogramming");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            testSave(em);
            tx.commit();
        } catch (Exception e) {
            System.out.println("error! : " + e.toString());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void testSave(EntityManager em) {

        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team1 = new Team("team1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1);
        em.persist(member2);
        em.persist(team1);
    }
}
