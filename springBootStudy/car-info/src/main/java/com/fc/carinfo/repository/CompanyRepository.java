package com.fc.carinfo.repository;

import com.fc.carinfo.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                          //Entity, Primary Key
public interface CompanyRepository extends JpaRepository<Company, Long>{
  
}
