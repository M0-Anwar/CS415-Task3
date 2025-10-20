package div.m0anwar.task3.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.*;


import div.m0anwar.task3.entity.Employee;

@RestController
public class EmployeeRestController {
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;

    private List<Employee> allEmployees;

    public EmployeeRestController() {
        employee1 = new Employee(1, "employee1", "employee1@gmail.com");
        employee2 = new Employee(2, "employee2", "employee2@gmail.com");
        employee3 = new Employee(3, "employee3", "employee3@gmail.com");
        employee4 = new Employee(4, "employee4", "employee4@gmail.com");

        if (allEmployees == null) {
            allEmployees = new ArrayList<>();
        }

        allEmployees.add(employee1);
        allEmployees.add(employee2);
        allEmployees.add(employee3);
        allEmployees.add(employee4);
    }

    // get all employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return allEmployees;
    }

    // get a specific employee using his id
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(int employeeId) {

        Employee theEmployee = null;

        for(Employee e: allEmployees) {
            if(e.getId() == employeeId) {
                theEmployee = e;
                break;
            }
        }

        if (theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    // add a new employee
    @PostMapping("/employees")
    public boolean addEmployee(Employee theEmployee) {

        boolean flag = allEmployees.add(theEmployee);
        
        return flag;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(int employeeId) {

        Employee tempEmployee = null;

        for(Employee e: allEmployees) {
            if(e.getId() == employeeId) {
                tempEmployee = e;
                break;
            }
        }

        // throw exception if null

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        allEmployees.remove(tempEmployee);

        return "Deleted employee id - " + employeeId;
    }

}














