package com.fc.carinfo.repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fc.carinfo.domain.Car;
//import com.fc.carinfo.repository.CarRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
// 패치 조인 적용 방법 
@Repository                                          //Entity, Primary Key
public class CarRepositoryCustomImpl implements CarRepositoryCustom {
  @PersistenceContext
  EntityManager em;

  @Override
  public List<Car> getCarListByFetchJoin(){
    //JPQL
    //Fetch Join 시 Page 기능 사용 불가 
    String query = "SELECT car FROM Car car join fetch car.company";
    List<Car> carList = em.createQuery(query, Car.class).getResultList();

    return carList;
  }

  
}
