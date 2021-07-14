package yg.study.msa.repository;

import yg.study.msa.model.entity.ReaderWebBookPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReaderWebBookPaymentRepository extends JpaRepository<ReaderWebBookPayment,Long> {

    List<ReaderWebBookPayment> findAllByReaderId(Long readerId);

    ReaderWebBookPayment findByReaderIdAndWebBookChapterId(Long readerId, Long webBookChapterId);
}
