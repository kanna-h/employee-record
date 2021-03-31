package com.findandreplace.employeerecord.controllers;
import com.findandreplace.employeerecord.exceptions.ResourceNotFoundException;
import com.findandreplace.employeerecord.models.Employee;
import com.findandreplace.employeerecord.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/employees",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return employeeService.getEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long employeeId)
            throws ResourceNotFoundException {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee){
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employee){
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id)
            throws ResourceNotFoundException {
        return employeeService.deleteEmployee(id);
    }

}
