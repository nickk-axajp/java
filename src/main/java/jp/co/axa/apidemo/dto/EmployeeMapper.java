package jp.co.axa.apidemo.dto;

import jp.co.axa.apidemo.entities.Employee;

public class EmployeeMapper {
    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setDepartment(employee.getDepartment());

        return dto;
    }

    public static Employee toEntity(EmployeeCreateDTO dto) {
        return toEntity(dto, null);
    }

    public static Employee toEntity(EmployeeCreateDTO dto, Long employeeId) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName(dto.getName());
        employee.setSalary(dto.getSalary());
        employee.setDepartment(dto.getDepartment());

        return employee;
    }
}
