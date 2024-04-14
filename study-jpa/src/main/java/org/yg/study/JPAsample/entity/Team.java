package org.yg.study.JPAsample.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Team extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonManagedReference
    @ToString.Exclude
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "team",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    // BatchSize 는 Eager 로 가져올 때 N + 1 문제를 해결하기 위해 사용한다.
    @BatchSize(size = 1000)
    private List<Unit> units = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
