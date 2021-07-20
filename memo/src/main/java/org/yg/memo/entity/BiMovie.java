package org.yg.memo.entity;

import lombok.*;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "posterList")
@Table(name="tbl_bi_movie")
public class BiMovie extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;
    private String title;

    //mappedBy 실제 데이터베이스에서 자신은 연관관계의 주인이 아니라는 것을 명시
    // Cascase 는 부모엔티티의 상태 변화를 자식 엔티티에게 어떻게 전파 할거냐에 대한 전략이다. ALL은 모든 상태변화를 전파한다.
    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "movie",
            cascade = CascadeType.ALL,
            orphanRemoval = true //removePoster 의 결과로 부모 엔티티에 참조를 잃은 자식 엔티티를 삭제하는 옵션
    ) // OneToMany 는 Fetch Type이 기본적으로 LAZY
    private List<BiPoster> posterList = new ArrayList<>();

    public void addPoster(BiPoster poster){
        poster.setIdx(this.posterList.size());
        poster.setMovie(this);
        posterList.add(poster);

    }

    public void removePoster(Long ino) {
        Optional<BiPoster> result = posterList.stream().filter(p->p.getIno() == ino).findFirst();

        result.ifPresent(p -> {
            p.setMovie(null);
            posterList.remove(p);
        });

        changeIdx();
    }

    private void changeIdx(){
        for (int i =0; i < posterList.size(); i++){
            posterList.get(i).setIdx(i);
        }
    }
}
