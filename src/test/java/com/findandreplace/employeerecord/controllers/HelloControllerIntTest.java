package com.findandreplace.employeerecord.controllers;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloController.class)
class HelloControllerIntTest {

    @Autowired
    MockMvc mockMvc;

    /*INTEGRATIONS TESTING AS WE ARE USING REST CONTROLLER*/
    @Test
    @Disabled
    void hello() throws  Exception{
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        MvcResult result = mockMvc.perform(request).andReturn();
        System.out.println("*****************RESULT "+ result.getResponse().getContentAsString());
        assertNotNull(result.getResponse().getContentAsString());
    }
}