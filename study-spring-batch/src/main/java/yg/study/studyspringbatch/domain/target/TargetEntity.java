package yg.study.studyspringbatch.domain.target;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "target_t")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String targetName;

  private Integer volume;

}
