package yg.study.msa.service;

import yg.study.msa.model.dto.WebBookChapterPaidDto;
import yg.study.msa.model.entity.ReaderWebBookPayment;
import yg.study.msa.model.entity.WebBookChapter;
import yg.study.msa.model.form.WebBookChapterPaymentForm;
import yg.study.msa.repository.ReaderWebBookPaymentRepository;
import yg.study.msa.repository.WebBookChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WebBookPaymentService {

    private final WebBookChapterRepository webBookChapterRepository;
    private final ReaderWebBookPaymentRepository readerWebBookPaymentRepository;

    public WebBookChapterPaidDto payment(
            Long readerId, WebBookChapterPaymentForm webBookChapterPaymentForm){

        ReaderWebBookPayment readerWebBookPayment  =
                readerWebBookPaymentRepository.findByReaderIdAndWebBookChapterId(readerId,webBookChapterPaymentForm.getWebBookChapterId());

        if(readerWebBookPayment != null){
            return null;    // 이미 결제 된 에러처리
        }

        WebBookChapter webBookChapter = webBookChapterRepository.findById(webBookChapterPaymentForm.getWebBookChapterId()).get();

        if(!webBookChapter.getPrice().equals(webBookChapterPaymentForm.getPrice())){
            return null;    //금액이 다름
        }


        readerWebBookPaymentRepository.save(
                ReaderWebBookPayment.builder()
                        .webBookChapterId(webBookChapterPaymentForm.getWebBookChapterId())
                        .readerId(readerId)
                        .paymentAmount(webBookChapterPaymentForm.getPrice())
                        .createdAt(LocalDateTime.now()).build()
        );

        return WebBookChapterPaidDto.from(webBookChapter);
    }
}
