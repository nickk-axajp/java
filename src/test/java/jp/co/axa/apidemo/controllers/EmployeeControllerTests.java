package jp.co.axa.apidemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.co.axa.apidemo.dto.EmployeeCreateDTO;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setSalary(5000);
        employee.setDepartment("IT");
    }

    @Test
    void testGetEmployeeByIdFound() throws Exception {
        Mockito.when(employeeService.getEmployee(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/v1/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void testGetEmployeeByIdNotFound() throws Exception {
        Mockito.when(employeeService.getEmployee(2L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/employees/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetEmployees() throws Exception {
        Mockito.when(employeeService.retrieveEmployees()).thenReturn(Arrays.asList(employee));
        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void testCreateEmployee() throws Exception {
        EmployeeCreateDTO createDTO = new EmployeeCreateDTO("Jane Doe", 6000, "HR");
        Employee saved = new Employee();
        saved.setId(2L);
        saved.setName("Jane Doe");
        saved.setSalary(6000);
        saved.setDepartment("HR");
        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(saved);

        mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.when(employeeService.deleteEmployee(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteEmployeeNotFound() throws Exception {
        Mockito.when(employeeService.deleteEmployee(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/v1/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        EmployeeCreateDTO updateDTO = new EmployeeCreateDTO("John Updated", 7000, "Finance");
        Mockito.when(employeeService.getEmployee(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateEmployeeNotFound() throws Exception {
        EmployeeCreateDTO updateDTO = new EmployeeCreateDTO("Not Found", 7000, "Finance");
        Mockito.when(employeeService.getEmployee(99L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/employees/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetEmployeesByDepartment() throws Exception {
        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Jane Doe");
        emp2.setSalary(6000);
        emp2.setDepartment("IT");
        Mockito.when(employeeService.getEmployeesByDepartment("IT"))
                .thenReturn(Arrays.asList(employee, emp2));

        mockMvc.perform(get("/api/v1/employees/department/IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].department").value("IT"))
                .andExpect(jsonPath("$[1].department").value("IT"));
    }
}
