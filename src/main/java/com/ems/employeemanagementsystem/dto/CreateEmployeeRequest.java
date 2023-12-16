package com.ems.employeemanagementsystem.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

import com.ems.employeemanagementsystem.model.Employee;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequest {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String emailId;

    public Employee to() {
        return Employee.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .emailId(this.emailId)
                .build();
    }
}