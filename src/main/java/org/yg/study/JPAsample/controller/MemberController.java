package org.yg.study.JPAsample.controller;

import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.yg.study.JPAsample.entity.Member;
import org.yg.study.JPAsample.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();
    }

    // 도메인 클래스 컨버트 
    // 스프링 JPA 가 ID 를 기반으로 Member 객체를 리턴해 준다. 
    // 그러나 권장 하지 않음 증말 간단할 때만 ~~~ 사용 
    // 영속성 컨택스트가 애매함으로 조회용으로만 사용 !!!!
    @GetMapping("/member2/{id}")
    public String findMember(@PathVariable("id") Member member){
        return member.getUsername();
    }

    //http://localhost:8080/members?page=0&size=3
    //http://localhost:8080/members?page=0&size=3&sort=id.desc
    @GetMapping("/members")
    public Page<Member>list(@PageableDefault(size = 5) Pageable pageable){
        return memberRepository.findAll(pageable);
    }


    @PostConstruct
    public void init(){
//        IntStream.range(0, 100).forEach(i->
//            memberRepository.save(new Member("userA" + i, i))
//        );
    
    }
    
}
