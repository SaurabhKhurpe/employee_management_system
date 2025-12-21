package com.ems.employeemanagementsystem.dto;

import lombok.*;
import jakarta.validation.constraints.NotNull;
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

    private String department;

    private String designation;

    private Double salary;

    public Employee to() {
        return Employee.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .emailId(this.emailId)
                .department(this.department)
                .designation(this.designation)
                .salary(this.salary)
                .build();
    }
}