package com.findandreplace.employeerecord.controllers;

import com.findandreplace.employeerecord.EmployeeRecordApplication;
import com.findandreplace.employeerecord.models.Employee;
import com.findandreplace.employeerecord.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
/*
@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
class EmployeeControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;
    @Test
    void getAllEmployees() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/");
        MvcResult result = mockMvc.perform(request).andReturn();
        System.out.println("*****************RESULT "+ result.getResponse().getContentAsString());
        assertEquals(400, result.getResponse().getStatus());
    }
}*/

/*THIS IS 100% INTEGRATION TEST AS WE ARE INTEGRATING WITH THE SERVICE LAYER AND DATA LAYER*/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = EmployeeRecordApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int PORT;
    private String getRootUrl(){
        return "http://localhost:" + PORT;
    }
    @Test
    void getAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl()+"/api/employees",
                HttpMethod.GET, entity, String.class);
        System.out.println("*************" + response.getBody());
        assertNotNull(response.getBody());
    }

    @Test
    void getEmployee(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> response = template.getForEntity(getRootUrl()+"/api/employees/2", String.class);
        assertEquals(302, response.getStatusCodeValue());

    }
}
