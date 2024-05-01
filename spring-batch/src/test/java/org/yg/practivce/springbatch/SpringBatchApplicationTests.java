package org.yg.practivce.springbatch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.yg.practivce.springbatch.application.job.JobScheduler;
import org.yg.practivce.springbatch.domain.meter.service.MeterService;

@SpringBootTest()
@ActiveProfiles("local")
class SpringBatchApplicationTests {
	@Autowired
	private MeterService meterService;
	@MockBean
	JobScheduler jobScheduler;

	@Test
	void updateMeterTest() {
		meterService.updateMeter();

	}

}
