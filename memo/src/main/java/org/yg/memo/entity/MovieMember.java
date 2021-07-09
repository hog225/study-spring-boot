package org.yg.memo.entity;

// Member 라는 이름이 좋을 듯 싶으나 Study 때문에 Member 클래스가 이미 존재하기 때문에 ...

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name= "m_member")
public class MovieMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    private String email; // PK

    private String pw;

    private String nickname;
}

