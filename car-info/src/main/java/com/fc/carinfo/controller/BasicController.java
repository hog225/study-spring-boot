package com.fc.carinfo.controller;
import com.fc.carinfo.service.CompanyInputDTO;
import com.fc.carinfo.service.CarInputDTO;
import com.fc.carinfo.service.CompanyService;
import com.fc.carinfo.service.CarService;
import com.fc.carinfo.domain.Company;
import com.fc.carinfo.domain.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.domain.*;
import java.util.*;

@Controller
@RequestMapping("/")
public class BasicController {
  @Autowired
  CompanyService companyService;

  @Autowired
  CarService carService;
  
  @RequestMapping("/")
  public String index(Model model){
    return "index";
  }

  @RequestMapping("/companyForm")
  public String companyForm(Model model){
    return "companyForm";
  }

  @RequestMapping("/saveCompany")
  public String saveCompany(@ModelAttribute(name="companyInputDTO")CompanyInputDTO companyInputDTO, Model model){
    companyService.saveCompanyInputDto(companyInputDTO);

    return "index";
  }

  // 추가 Study 필요 
  @RequestMapping("/companyList")
  public String companyList(@PageableDefault Pageable pageable, Model model){
    Page<Company> companyList = companyService.getCompanyPage(pageable);
    model.addAttribute("companyList", companyList);
    return "companyList";
  }

  @RequestMapping("/carForm")
  public String carList(Model model){
    List<Company> companyList = companyService.findAll();
    model.addAttribute("companyList", companyList);
    return "carForm";
  }

  @RequestMapping("/saveCar")
  public String saveCar(@ModelAttribute(name="carInputDTO")CarInputDTO carInputDTO, Model model){
    carService.saveCarInputDto(carInputDTO);

    return "index";
  }

  @RequestMapping("/carList")
  public String carList(@PageableDefault Pageable pageable, Model model){
    Page<Car> carList = carService.getCarPage(pageable);
    model.addAttribute("carList", carList);
    return "carList";
  }

  @RequestMapping("/carListAll")
  public String carListAll(Model model){
    List<Car> carList = carService.getCarListAll();
    model.addAttribute("carList", carList);
    return "carListAll";
  }


  @RequestMapping("/carListNoPage")
  public String carListNoPage(Model model){
    List<Car> carList = carService.getCarList();
    model.addAttribute("carList", carList);
    return "carListNoPage";
  }

}
