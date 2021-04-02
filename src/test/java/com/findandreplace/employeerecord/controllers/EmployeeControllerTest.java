package com.findandreplace.employeerecord.controllers;

import com.findandreplace.employeerecord.EmployeeRecordApplication;
import com.findandreplace.employeerecord.Repository.EmployeeRepository;
import com.findandreplace.employeerecord.services.EmployeeService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeRecordApplication.class)
class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void contextLoads() {

    }

    /*THIS IS NOT 100% UNIT TEST BECAUSE WE ARE INTEGRATING WITH THE REST CONTROLLER
    * NOT TOTALLY INTEGRATION TEST BECAUSE WE ARE MOCKING THE SERVICE AND REPOSITORY*/
    @Disabled
    @Test
    void testGetEmployees() throws Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/api/employees/").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(request).andReturn();
        System.out.println("************Response *****"+ mvcResult.getResponse().getContentAsString());
    }
}