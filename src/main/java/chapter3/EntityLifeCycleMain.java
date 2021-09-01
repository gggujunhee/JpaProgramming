package chapter3;

import chapter2.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityLifeCycleMain {
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

        //비영속
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUserName("junhee");
        member.setAge(2);

        //영속
        //em.find() 나 JPQL을 사용해서 조회한 엔티티도 영속상태로 됨
        em.persist(member);

        //준영속 - 영속성 컨텍스트에서 해당 엔티티를 지워버림
        // em.close() 나 em.clear() 하면 영속성 컨텍스트 안의 모든 엔티티들이 준영속 상태로 됨
        em.detach(member);

        //삭제
        em.remove(member);
    }
}
