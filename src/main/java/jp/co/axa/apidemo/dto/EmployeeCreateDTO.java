package jp.co.axa.apidemo.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeCreateDTO {
    @NotBlank
    @Size(max=100)
    private String name;

    @NotNull
    @Min(0)
    private Integer salary;

    @NotBlank
    @Size(max=50)
    private String department;

    public EmployeeCreateDTO() {
    }

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
