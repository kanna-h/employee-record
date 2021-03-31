package com.findandreplace.employeerecord.services;

import com.findandreplace.employeerecord.Repository.EmployeeRepository;
import com.findandreplace.employeerecord.exceptions.ResourceNotFoundException;
import com.findandreplace.employeerecord.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAll());
    }

    public ResponseEntity<Employee> getEmployee(Long employeeId)
            throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: "+ employeeId));
        return ResponseEntity.status(HttpStatus.FOUND).body(employee);
    }

    public ResponseEntity<Employee> createEmployee(Employee employee){
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
    }

    public ResponseEntity<Employee> updateEmployee(Long employeeId, Employee employee){
        return employeeRepository.findById(employeeId)
                .map(e-> {
                    e.setFirstName(employee.getFirstName());
                    e.setLastName(employee.getLastName());
                    e.setEmail(employee.getEmail());
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeRepository.save(e));
                })
                .orElseGet(()->{
                    employee.setEmployeeId(employeeId);
                    return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employee));
                });
    }

    public ResponseEntity<?> deleteEmployee(Long employeeId)
            throws ResourceNotFoundException{
        final Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id: "+ employeeId));
        employeeRepository.delete(employee);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}
