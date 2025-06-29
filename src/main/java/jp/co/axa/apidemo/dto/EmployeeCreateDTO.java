package jp.co.axa.apidemo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EmployeeCreateDTO {
    @NotBlank
    private String name;
    @NotNull
    private Integer salary;
    @NotBlank
    private String department;

    public EmployeeCreateDTO() {}

    public EmployeeCreateDTO(String name, Integer salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
