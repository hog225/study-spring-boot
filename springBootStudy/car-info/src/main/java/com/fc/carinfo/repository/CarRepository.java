package com.fc.carinfo.repository;

import com.fc.carinfo.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository                                          //Entity, Primary Key
public interface CarRepository extends JpaRepository<Car, Long>{
  @Query(value = "SELECT car FROM Car car join fetch car.company")
  List<Car> getCarListByFetchJoin();
}
