package yg.study.msa.service;

import yg.study.msa.model.entity.Reader;
import yg.study.msa.model.form.RegisterReaderForm;
import yg.study.msa.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
/**
 * Reader에 대한 CRUD
 */
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository ReaderRepository;

    public Long registerReader(RegisterReaderForm registerReaderForm) {

        return ReaderRepository.save(
                Reader.builder()
                        .name(registerReaderForm.getName())
                        .createdAt(LocalDateTime.now()).build()
        ).getReaderId();

    }
}
