package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> retrieveEmployees();

    Optional<Employee> getEmployee(Long employeeId);

    Employee saveEmployee(Employee employee);

    Boolean deleteEmployee(Long employeeId);

    void updateEmployee(Employee employee);

    List<Employee> getEmployeesByDepartment(String department);
}