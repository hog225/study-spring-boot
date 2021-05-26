package com.fc.carinfo.repository;

import com.fc.carinfo.domain.Car;
import java.util.*;

// 패치 조인 적용 방법 
                          
public interface CarRepositoryCustom {
  List<Car> getCarListByFetchJoin();
  
}
