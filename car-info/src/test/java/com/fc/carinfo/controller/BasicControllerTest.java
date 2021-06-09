package com.fc.carinfo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.fc.carinfo.domain.Car;
import com.fc.carinfo.domain.Company;
import com.fc.carinfo.service.CarService;
import com.fc.carinfo.service.CompanyService;

import java.util.*;
import org.mockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

// UNIT API 테스트 
@WebMvcTest(BasicController.class)
class BasicControllerTest {

//     Given : 시나리오 진행에 필요한 값을 설정한다.

// When : 시나리오를 진행하는데 필요한 조건을 명시한다.

// Then : 시나리오를 완료했을 때 보장해야 하는 결과를 명시한다.

    @Autowired
    MockMvc mvc;

    @MockBean
    CompanyService companyService;

    @MockBean
    CarService carService;

	@Test
    @DisplayName("Car Retrieve")
	void getCarListTest() throws Exception{
        Company company = new Company("Hyundai", "Korea");
        List<Car> cars = new ArrayList<>();
        
        cars.add(Car.builder().modelName("i30").company(company).passengerCapacity(5).build());
        given(carService.getCarListAll()).willReturn(cars); // getCarListAll을 요청하면 

        mvc.perform(get("/carListAll"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("i30")));
	}




}
