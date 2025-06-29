package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> retrieveEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Employee saveEmployee(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    public Boolean deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            return false;
        }

        employeeRepository.deleteById(employeeId);

        return true;
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}