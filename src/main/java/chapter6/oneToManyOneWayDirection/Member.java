package chapter6.oneToManyOneWayDirection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
