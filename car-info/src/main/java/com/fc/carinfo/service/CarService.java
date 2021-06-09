package com.fc.carinfo.service;
// Interface 로 구현하는게 좋나 ? ????? 

import org.springframework.transaction.annotation.Transactional;


import com.fc.carinfo.domain.Car;
import com.fc.carinfo.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import org.springframework.stereotype.Service;
import java.util.*;


@Service
@Transactional
public class CarService {
  @Autowired
  CarRepository carRepository;

  @Autowired
  CarRepositoryCustom carRepositoryCustom;

  @Autowired
  CompanyService companyService;

  @Transactional
  public Car saveCarInputDto(CarInputDTO carInputDTO){
    Car car = new Car();
    car.setModelName(carInputDTO.getModelName());
    car.setPassengerCapacity(carInputDTO.getPassengerCapacity());
    car.setCompany(companyService.find(carInputDTO.getCompanyId()));

    car.setCreatedAt(new Date());
    car.setUpdatedAt(new Date());

    return carRepository.save(car);
  }

  public Page<Car> getCarPage(Pageable pageable){
    int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
    pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));

    // 아래 findAll은 Spring JPA 가 자동 생성 해줌
    return carRepository.findAll(pageable);

  }
  public List<Car> saveAll(List<Car> car){
    return carRepository.saveAll(car);
  }

  public List<Car> getCarListAll(){
      return carRepository.findAll();
  }

  public List<Car> getCarList(){
    //return carRepositoryCustom.getCarListByFetchJoin();
    return carRepository.getCarListByFetchJoin();

  }

  
}
