package com.findandreplace.employeerecord.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class HelloControllerTest {
    /*THIS IS 100% UNIT TEST BECAUSE NO INTEGRATION WITH OTHER SERVICES, except for objects*/
    @Test
    void TestHello() {
        HelloController controller = new HelloController();
        String response = controller.hello("World!");
        assertEquals("Hello World!", response);
    }
}