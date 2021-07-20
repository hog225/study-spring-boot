package org.yg.memo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "movie")
@Table(name="tbl_bi_poster")
public class BiPoster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String fname;
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    private BiMovie movie;

    // BiMovie 객체에서 BiPoster를 다루기 위한 ........
    public void setIdx(int idx){
        this.idx = idx;
    }
    // 사실 Entity에서 Set 메서드는 지양 하는게 좋다.
    public void setMovie(BiMovie movie){
        this.movie = movie;
    }
}
