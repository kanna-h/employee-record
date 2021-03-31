package com.findandreplace.employeerecord.controllers;
import com.findandreplace.employeerecord.exceptions.ResourceNotFoundException;
import com.findandreplace.employeerecord.models.Employee;
import com.findandreplace.employeerecord.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/employees",
                produces = MediaType.APPLICATION_JSON_VALUE,
                consumes = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployees() throws ResourceNotFoundException {
        List<EntityModel<Employee>> employees = new ArrayList<>();
        for (Employee emp : employeeService.getEmployees().getBody()) {
            employees.add(EntityModel.of(emp,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEmployee(emp.getEmployeeId())).withSelfRel()));
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Long employeeId)
            throws ResourceNotFoundException {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Valid Employee employee) throws ResourceNotFoundException {
        ResponseEntity<Employee> responseEntity = employeeService.createEmployee(employee);
        Employee emp = responseEntity.getBody();
        EntityModel<Employee> resource = EntityModel.of(emp);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEmployee(emp.getEmployeeId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
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
