package com.fc.carinfo;

import javax.annotation.PostConstruct;

import com.fc.carinfo.domain.Company;
import com.fc.carinfo.service.CompanyService;

import com.fc.carinfo.domain.Car;

import com.fc.carinfo.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class AppInitializer {
  @Autowired
  CompanyService companyService;

  @Autowired
  CarService carService;

  @PostConstruct
  private void init(){
    List<Company> companyList = new ArrayList<>();
    companyList.add(new Company("페라리", "이탈리아"));
    companyList.add(new Company("케딜락", "미국"));
    companyList.add(new Company("현대", "한국"));
    companyList.add(new Company("르노", "프랑스"));
    companyList.add(new Company("볼보", "스웨덴"));
    companyList.add(new Company("람보르기니", "이탈리아"));
    companyList.add(new Company("맥라렌", "영국"));
    companyList.add(new Company("마세라티", "이탈리아"));
    companyList.add(new Company("파가니", "이탈리아"));
    companyList.add(new Company("니싼", "일본"));    
    companyList.add(new Company("도요타", "일본"));    
    companyList.add(new Company("폭스박겐", "독일"));    
    companyList.add(new Company("BMW", "미국"));    
    companyList.add(new Company("페라리q", "이탈리아"));
    companyList.add(new Company("케딜락q", "미국"));
    companyList.add(new Company("현대q", "한국"));
    companyList.add(new Company("르노q", "프랑스"));
    companyList.add(new Company("볼보q", "스웨덴"));
    companyList.add(new Company("람보르기니q", "이탈리아"));
    companyList.add(new Company("맥라렌q", "영국"));
    companyList.add(new Company("마세라티q", "이탈리아"));
    companyList.add(new Company("파가니q", "이탈리아"));
    companyList.add(new Company("니싼q", "일본"));    
    companyList.add(new Company("도요타q", "일본"));    
    companyList.add(new Company("폭스박겐q", "독일"));    
    companyList.add(new Company("BMWq", "미국"));    
    companyService.saveAll(companyList);

    
    List<Car> carList = new ArrayList<>();
    carList.add(new Car("M1", companyList.get(12), 5));
    carList.add(new Car("M2", companyList.get(12), 5));
    carList.add(new Car("M3", companyList.get(12), 5));
    carList.add(new Car("M4", companyList.get(12), 5));
    carList.add(new Car("M5", companyList.get(12), 5));
    carList.add(new Car("M6", companyList.get(12), 5));
    carList.add(new Car("M7", companyList.get(12), 5));
    carList.add(new Car("M7", companyList.get(12), 5));

    carList.add(new Car("소나타", companyList.get(2), 5));
    carList.add(new Car("아반떼", companyList.get(2), 5));
    carList.add(new Car("아이오닉", companyList.get(2), 5));
    carList.add(new Car("gv80", companyList.get(2), 5));
    carList.add(new Car("그렌저", companyList.get(2), 5));
    carList.add(new Car("아슬란", companyList.get(2), 5));
    carList.add(new Car("투스카닉", companyList.get(2), 5));
    carList.add(new Car("gv70", companyList.get(2), 5));
    carService.saveAll(carList);


  }

}