package org.yg.memo.repository;


import org.yg.memo.entity.Memo;

import java.util.*;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;

@SpringBootTest
public class MemoRepositoryTests{

  @Autowired
  MemoRepository memoRepository;

  @Test
  public void testClass(){
    System.out.println(memoRepository.getClass().getName());
  }

  @Test
  public void testInsertDummies(){
    IntStream.rangeClosed(1, 100).forEach(i->{
      Memo memo = Memo.builder().memoText("Sample..." + i).build();
      memoRepository.save(memo);
    });
  }

  @Test
  public void testSelect1(){
    Long mno = 100L;
    // Transaction 즉시 수행 findById
    Optional<Memo> result = memoRepository.findById(mno);

    System.out.println("==================");

    if (result.isPresent()){
      Memo memo = result.get();
      System.out.println(memo);
    }
    

    
  }

  @Transactional
  @Test
  public void testSelect2(){
    Long mno = 100L;
    // Transaction 수행 안하고 객체 사용할 때 수행 
    Memo memo = memoRepository.getOne(mno);
  
    System.out.println("==================");
    System.out.println(memo);

    
  }

  
  @Test
  public void testDelete(){
    Long mno = 100L;
    // Transaction 수행 안하고 객체 사용할 때 수행 
    memoRepository.deleteById(mno);  
  }

  @Test
  public void testPageDefault(){
    //Spring data jpa 사용시 페이지 처리는 0부터 
    Pageable pageable = PageRequest.of(0, 10);
    Page<Memo> result = memoRepository.findAll(pageable);
    System.out.println(result);
    System.out.println("===============================");
    
    System.out.println("Total Pages: "+ result.getTotalPages());
    System.out.println("Total Count: "+ result.getTotalElements());
    System.out.println("Page Number: "+ result.getNumber());
    System.out.println("Page Size: "+ result.getSize());
    System.out.println("has next page?: "+ result.hasNext());
    System.out.println("first page?: "+ result.isFirst());
    for (Memo memo : result.getContent()){
      System.out.println(memo);
    }
  }

  @Test
  public void testSort(){

    Sort sort1 = Sort.by("mno").descending();
    Pageable pageable = PageRequest.of(0, 10, sort1);

    Page<Memo> result = memoRepository.findAll(pageable);
    result.get().forEach(memo -> {
      System.out.println(memo);
    });
  }
  @Test
  public void testQueryString(){
    List<Memo> memoList = memoRepository.findByMnoBetweenOrderByMnoDesc(0L, 5L);
    for (Memo memo: memoList){
      System.out.println(memo);
    }
  }

  @Test
  public void testQueryMethodWithPagable(){
    Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
    result.get().forEach(memo->System.out.println(memo));
  }

  //deletBy 는 한개한개씩 지우기 때문에 성능적으로 좋지않다. 
  @Commit
  @Transactional
  @Test
  public void testDeleteQueryMethod() {
    memoRepository.deleteMemoByMnoLessThan(10L);
  }

  @Commit
  @Test 
  public void testUpdate() {
    memoRepository.updateMemoText(11L, "update Memo Text value");
    Memo testMemo = Memo.builder().mno(12L).memoText("update memo with Object").build();
    memoRepository.updateMemoText(testMemo);
    // @Query 에서 Pageable 사용 
    Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
    Page<Memo> result = memoRepository.getListWithQuery(50L, pageable);
    result.get().forEach(memo->System.out.println(memo));

    Page<Object[]> result2 = memoRepository.getListWithQueryObject(60L, pageable);
    result2.get().forEach(memo->System.out.println(memo));

    List<Object[]> result3 = memoRepository.getNativeResult();
    
    System.out.println(result3.get(0));

  }




}