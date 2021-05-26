package org.yg.memo.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class SampleDTO {
  private Long sno;
  private String first;
  private String last;
  private LocalDateTime regTime;
}