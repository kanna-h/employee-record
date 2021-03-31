package com.findandreplace.employeerecord.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    private Long employeeId;
    @NotEmpty(message = "First Name must not be empty")
    private String firstName;
    @NotEmpty(message = "Last Name must not be empty")
    private String lastName;
    @NotEmpty(message = "Email id must not be empty")
    @Email(message = "Invalid email address")
    private String email;

}
