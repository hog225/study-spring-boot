package org.yg.memo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie") // 연관관계를 exclude 안하면 무한루프로 StackOverflow 발생한다.
public class MovieImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum; //PK

    private String uuid;

    private String imgName;
    private String path;


    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie; // Bord 와 Member 간의 관계 Many: One, FK: PK


}
