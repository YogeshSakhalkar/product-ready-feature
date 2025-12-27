package com.example.prod_ready_feature.client.Impl;

import com.example.prod_ready_feature.Advice.ApiResponse;
import com.example.prod_ready_feature.client.EmployeeClient;
import com.example.prod_ready_feature.dto.EmployeeDto;
import com.example.prod_ready_feature.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger logger = LoggerFactory.getLogger(EmployeeClientImpl.class);



    @Override
    public List<EmployeeDto> getAllEmployee() {
        try {
            ApiResponse<List<EmployeeDto>> employeeDtoList = restClient.get()
                    .uri("employee")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)->{
                        // System.out.println("error message"+res.getBody().readAllBytes());
                        logger.error( new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("no employee found");
                    })
                    .body(new ParameterizedTypeReference<>() {

                    });
            logger.debug("sucessfully recived data from the table");
            logger.trace("Recived employee list",employeeDtoList.getData());
            return employeeDtoList.getData();
        } catch (Exception e) {
            logger.error("Exception occured in getAllEmployee",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDto> employeeDtoApiResponse =restClient.get()
                    .uri("employee/{employeeId}",employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)->{
                        // System.out.println("error message"+res.getBody().readAllBytes());
                        logger.error( new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("no employee id found");
                    })
                    .body(new ParameterizedTypeReference<>(){

                    });
            logger.debug("sucessfully recived data from the table");
            logger.trace("Recived employee list",employeeDtoApiResponse.getData());
            return employeeDtoApiResponse.getData();
        }catch (Exception e){
            logger.error("Exception occured in getEmployeeById",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employeeDto) {

        logger.trace("trying to access {}",employeeDto);
        try {
            ResponseEntity<ApiResponse<EmployeeDto>> employeeDtoApiResponse = restClient.post()
                    .uri("employee")
                    .body(employeeDto)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req, res)->{
                       // System.out.println("error message"+res.getBody().readAllBytes());
                        System.out.println( new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create new employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {

                    });
            logger.debug("sucessfully recived data from the table");
            logger.trace("Recived employee list",employeeDtoApiResponse);
            return employeeDtoApiResponse.getBody().getData();
        }catch (Exception w){
            logger.error("Exception occured in getEmployeeById",w.getMessage());
            throw new RuntimeException(w);
        }

    }

}
