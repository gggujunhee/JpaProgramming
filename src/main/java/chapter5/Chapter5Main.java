package chapter5;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class Chapter5Main {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaprogramming");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            //testSave(em);
            //queryLogicJoin(em);
            //updateRelation(em);
            //deleteRelation(em);
            //biDirection(em);
            //testSaveNonOwner(em);
            //test순수한객체_양방향();
            testORM_양방향(em);
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
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    private static void queryLogicJoin(EntityManager em) {
        testSave(em);
        String jpql = "select m from Member m join m.team t where " + "t.name=:teamName";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : resultList) {
            System.out.println("[query] member.username=" + member.getUsername());
        }
    }

    private static void updateRelation(EntityManager em) {
        testSave(em);
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);

        printAllMember(em);
    }

    private static void deleteRelation(EntityManager em) {
        testSave(em);

        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);

        printAllMember(em);
    }

    private static void printAllMember(EntityManager em) {
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member m : members) {
            System.out.println(m.getId() + " " + m.getUsername() + " " + (m.getTeam() == null ? null : m.getTeam().getId()));
        }
    }

    private static void biDirection(EntityManager em) {
        testSave(em);
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for (Member member : members) {
            System.out.println("member.username = " + member.getUsername());
        }
    }


    //Team은 연관관계 주인이 아니기 때문에 member를 추가해도 반영이 안됨
    private static void testSaveNonOwner(EntityManager em) {
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        Team team = new Team("teams1", "팀1");
        team.getMembers().add(member1);
        team.getMembers().add(member2);

        em.persist(team);
    }

    private static void test순수한객체_양방향() {
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        member1.setTeam(team1);
        team1.getMembers().add(member1);
        member2.setTeam(team1);
        team1.getMembers().add(member2);

        List<Member> members = team1.getMembers();
        System.out.println("members.size = " + members.size());
    }

    private static void testORM_양방향(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");

        member1.setTeam(team1);
//        team1.getMembers().add(member1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");

        member2.setTeam(team1);
//        team1.getMembers().add(member2);
        em.persist(member2);

        List<Member> members = team1.getMembers();
        System.out.println("member.size = " + members.size());
    }
}
