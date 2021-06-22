package org.yg.memo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer") // 연관관계를 exclude 안하면 무한루프로 StackOverflow 발생한다.
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno; //PK

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer; // Bord 와 Member 간의 관계 Many: One, FK: PK

}
