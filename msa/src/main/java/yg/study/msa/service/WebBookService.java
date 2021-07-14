package yg.study.msa.service;

import yg.study.msa.model.dto.WebBookChapterDto;
import yg.study.msa.model.dto.WebBookDto;
import yg.study.msa.model.entity.ReaderWebBookPayment;
import yg.study.msa.repository.ReaderWebBookPaymentRepository;
import yg.study.msa.repository.WebBookChapterRepository;
import yg.study.msa.repository.WebBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebBookService {

    private final WebBookRepository webBookRepository;
    private final WebBookChapterRepository webBookChapterRepository;
    private final ReaderWebBookPaymentRepository readerWebBookPaymentRepository;

    public List<WebBookDto> getWebBookList() {
        return webBookRepository.findAll().stream().map(webBook -> WebBookDto.from(webBook))
                .collect(Collectors.toList());
    }

    public List<WebBookChapterDto> getWebBookList(Long readerId, Long webBookId) {

        List<ReaderWebBookPayment> readerWebBookPayments = readerWebBookPaymentRepository.findAllByReaderId(readerId);

        List<WebBookChapterDto> webBookChapterDtos = webBookChapterRepository.findAllById(List.of(webBookId)).stream()
                .map(webBookChapter -> WebBookChapterDto.from(webBookChapter))
                .collect(Collectors.toList());

        readerWebBookPayments.forEach(readerWebBookPayment -> {
            WebBookChapterDto webBookChapterDto = webBookChapterDtos.stream().filter(webBookChapterDto1 ->
                    webBookChapterDto1.getWebBookChapterId().equals(readerWebBookPayment.getWebBookChapterId())).findFirst().get();
            if (webBookChapterDto != null) {
                webBookChapterDto.setIsPaid(true);
            } else {
                webBookChapterDto.setIsPaid(false);
            }
        });
        return webBookChapterDtos;
    }
}
