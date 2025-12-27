package com.example.prod_ready_feature;

import com.example.prod_ready_feature.client.EmployeeClient;
import com.example.prod_ready_feature.dto.EmployeeDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProdReadyFeatureApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	@Order(3)
	void getAllEmployeeTest(){
		List<EmployeeDto> employeeDtosList = employeeClient.getAllEmployee();
		System.out.println(employeeDtosList);
	}

	@Test
	@Order(2)
	void getEmployeeById(){
		EmployeeDto employeeDto = employeeClient.getEmployeeById(1L);
		System.out.println(employeeDto);
	}

	@Test
	@Order(1)
	void createNewEmployeeTest(){
		EmployeeDto employeeDto = new EmployeeDto
				(null,"Yogesh",29,"yogeshDX@gmail.com",true, LocalDate.now(),"ADMIN", 89000.0);
		EmployeeDto saveEmployeeDto = employeeClient.createNewEmployee(employeeDto);
		System.out.println(saveEmployeeDto);
	}

}
