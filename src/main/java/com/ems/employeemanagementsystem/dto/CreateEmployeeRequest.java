package com.ems.employeemanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import com.ems.employeemanagementsystem.model.Employee;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEmployeeRequest {
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email ID is required")
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