package jp.co.axa.apidemo.dto;


public class EmployeeDTO {
    private Long id;
    private String name;
    private Integer salary;
    private String department;

    public EmployeeDTO() {}

    public EmployeeDTO(Long id, String name, Integer salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
