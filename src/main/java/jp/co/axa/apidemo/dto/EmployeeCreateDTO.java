package jp.co.axa.apidemo.dto;

public class EmployeeCreateDTO {
    private String name;
    private Integer salary;
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
