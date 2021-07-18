package yg.study.msa.controller;

import yg.study.msa.model.dto.WebBookChapterDto;
import yg.study.msa.model.dto.WebBookChapterPaidDto;
import yg.study.msa.model.dto.WebBookDto;
import yg.study.msa.model.form.RegisterReaderForm;
import yg.study.msa.model.form.WebBookChapterPaymentForm;
import yg.study.msa.service.ReaderService;
import yg.study.msa.service.WebBookPaymentService;
import yg.study.msa.service.WebBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService ReaderService;
    private final WebBookService webBookService;
    private final WebBookPaymentService webBookPaymentService;

    @PostMapping("/")
    // RequestParam 일경우 ContentType이 무조건 application/json 이어야 한다.
    public ResponseEntity<Long> registerReader(@RequestParam RegisterReaderForm registerReaderForm){
        return ResponseEntity.ok(ReaderService.registerReader(registerReaderForm));
    }

    @GetMapping("/webBook")
    public ResponseEntity<List<WebBookDto>> getWebBookList(){
        return ResponseEntity.ok(webBookService.getWebBookList());
    }

    @GetMapping("/{readerId}/webBook/{webBookId}/chapter")
    public ResponseEntity<List<WebBookChapterDto>> getWebBookChapterList(
            @PathVariable(value = "readerId") Long readerId,
            @PathVariable(value = "webBookId") Long webBookId){
        return ResponseEntity.ok(webBookService.getWebBookList(readerId,webBookId));
    }

    @PostMapping("/{readerId}/payment/")
    public ResponseEntity<WebBookChapterPaidDto> paymentWebBookChapter(
            @PathVariable(value = "readerId") Long readerId,
            @RequestBody WebBookChapterPaymentForm webBookChapterPaymentForm){
        return ResponseEntity.ok(webBookPaymentService.payment(readerId,webBookChapterPaymentForm));
    }
}
