package yg.study.msa.service;

import yg.study.msa.model.entity.Writer;
import yg.study.msa.model.form.RegisterWriterForm;
import yg.study.msa.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
/**
 * writer에 대한 CRUD
 */
@RequiredArgsConstructor
public class WriterService {

    private final WriterRepository writerRepository;

    public Long registerWriter(RegisterWriterForm registerWriterForm) {

        return writerRepository.save(
                Writer.builder()
                        .name(registerWriterForm.getName())
                        .createdAt(LocalDateTime.now()).build()
        ).getWriterId();

    }
}
