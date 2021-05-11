package org.yg.memo.repository;


import org.yg.memo.entity.Memo;

import java.util.*;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
}