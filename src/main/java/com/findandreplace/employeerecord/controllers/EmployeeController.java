package com.findandreplace.employeerecord.controllers;
import com.findandreplace.employeerecord.Repository.EmployeeRepository;
import com.findandreplace.employeerecord.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId)
            throws Exception{
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new Exception("Employee not found for id: "+ employeeId));
        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Valid Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employee){
        return employeeRepository.findById(id)
                .map(e-> {
                    e.setFirstName(employee.getFirstName());
                    e.setLastName(employee.getLastName());
                    e.setEmail(employee.getEmail());
                    return employeeRepository.save(e);
                })
                .orElseGet(()->{
                    employee.setEmployeeId(id);
                    return employeeRepository.save(employee);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id){
        final Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee with the given ${id} not found"));
        employeeRepository.delete(employee);

    }

}
