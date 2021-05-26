package com.fc.carinfo.service;
// Interface 로 구현하는게 좋나 ? ????? 

import org.springframework.transaction.annotation.Transactional;


import com.fc.carinfo.domain.Company;
import com.fc.carinfo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;
import java.util.*;


@Service
@Transactional
public class CompanyService {
  @Autowired
  CompanyRepository companyRepository;

  @Transactional
  public Company saveCompanyInputDto(CompanyInputDTO companyInputDTO){
    Company company = new Company();
    company.setCompanyName(companyInputDTO.getCompanyName());
    company.setCompanyNation(companyInputDTO.getCompanyNation());
    company.setCreatedAt(new Date());
    company.setUpdatedAt(new Date());
    return companyRepository.save(company);
    

  }

  public Company save(Company company){
    
    return companyRepository.save(company);
    

  }

  public List<Company> saveAll(List<Company> company){
    return companyRepository.saveAll(company);
  }

  public List<Company> findAll(){
    return companyRepository.findAll();
  }
  public Company find(Long companyId){
    // Rturn Type이 Optional 이기 때문에 get()을 추가하여 객체를 가져 온다. 
    return companyRepository.findById(companyId).get();
  }


  public Page<Company> getCompanyPage(Pageable pageable){
    int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
    pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));

    // 아래 findAll은 Spring JPA 가 자동 생성 해줌
    return companyRepository.findAll(pageable);

  }

  
}
