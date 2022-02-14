package yg.study.studyspringbatch.batchjob.metering.step.validation;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class ValidationConfiguration implements JobParametersValidator {

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        if (parameters.getString("name") == null) {
            throw new JobParametersInvalidException("name parameters is not found");
        }
    }
}
