package yg.study.studyspringbatch.domain.meter;

import java.lang.annotation.Target;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.LockAcquisitionException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import yg.study.studyspringbatch.domain.target.TargetEntity;
import yg.study.studyspringbatch.domain.target.TargetRepository;

@RequiredArgsConstructor
@Slf4j
@Service
public class MeterService {
  private final TargetRepository targetRepository;
  private final MeterLogRepository meterLogRepository;

  public void apiCall(boolean exceptionPoint) {

    if (exceptionPoint) {
      System.out.println();
      throw new RuntimeException("API CALLED run time Exception");
    } else {
      System.out.println("API CALLED");
    }

  }

  public void updateMeter(String code) {
    List<TargetEntity> entities = targetRepository.findAll();

    Integer index = 0;
    var now = ZonedDateTime.now();
    for (var target : entities) {
      try {
        var newCount = target.getVolume() + 1;

        index++;

        //api call
        apiCall(index % 2 == 0);

        if (index == 5) {
          throw new LockAcquisitionException("ee", new SQLException());
        }

        target.setVolume(newCount);
        targetRepository.save(target);

        meterLogRepository.save(MeterLogEntity.builder()
            .targetName(target.getTargetName())
                .time(ZonedDateTime.now())
            .build());
      } catch (OptimisticLockingFailureException | LockAcquisitionException e) {
        log.error("OptimisticLockingFailureException");
        return;
      } catch (Exception e) {
        log.error("error Ocuured Continue", e);
      }

    }



  }

}
