package chapter6.oneToManyOneWayDirection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "TEAM_ID") // MEMBER νμ΄λΈμ TEAM_ID (FK)
    private List<Member> members = new ArrayList<>();

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }
}
