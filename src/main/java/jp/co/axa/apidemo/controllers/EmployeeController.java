package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.dto.EmployeeCreateDTO;
import jp.co.axa.apidemo.dto.EmployeeDTO;
import jp.co.axa.apidemo.dto.EmployeeMapper;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.retrieveEmployees().stream().map(EmployeeMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Optional<Employee> employee = employeeService.getEmployee(employeeId);

        return employee.map(e -> ResponseEntity.ok(EmployeeMapper.toDTO(e))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO) {
        Employee saved = employeeService.saveEmployee(EmployeeMapper.toEntity(employeeCreateDTO));
        EmployeeDTO response = EmployeeMapper.toDTO(saved);

        return ResponseEntity.created(
                java.net.URI.create("/api/v1/employees/" + response.getId())
        ).body(response);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Boolean deleted = employeeService.deleteEmployee(employeeId);

        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeCreateDTO employeeCreateDTO,
                                   @PathVariable(name = "employeeId") Long employeeId) {
        Optional<Employee> emp = employeeService.getEmployee(employeeId);

        if (emp.isPresent()) {
            Employee updated = EmployeeMapper.toEntity(employeeCreateDTO);
            updated.setId(employeeId);
            employeeService.updateEmployee(updated);

            return ResponseEntity.ok(EmployeeMapper.toDTO(updated));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
