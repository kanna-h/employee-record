package com.findandreplace.employeerecord.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employees")

@SequenceGenerator(name="seq", initialValue=6, allocationSize=100)
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    @Column(name = "employee_id")
    private Long employeeId;

    @NotEmpty(message = "First Name must not be empty")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last Name must not be empty")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email id must not be empty")
    @Email(message = "Invalid email address")
    @Column(name = "email")
    private String email;

}
