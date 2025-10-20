package div.m0anwar.task3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import div.m0anwar.task3.entity.Employee;
import div.m0anwar.task3.rest.EmployeeRestController;


@SpringBootTest
class Task3ApplicationTests {

	private List<Employee> allEmployees;
	private Employee employee1;
	private Employee employee2;
	private Employee employee3;
	private Employee employee4;

	@Autowired
	private EmployeeRestController employeeRestController;

	@BeforeEach
	public void setUp() {
		employee1 = new Employee(1, "employee1", "employee1@gmail.com");
		employee2 = new Employee(2, "employee2", "employee2@gmail.com");
		employee3 = new Employee(3, "employee3", "employee3@gmail.com");
		employee4 = new Employee(4, "employee4", "employee4@gmail.com");

    // allEmployees = Arrays.asList(employee1, employee2, employee3, employee4);

		if (allEmployees == null) {
		}
		allEmployees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3, employee4));

		employeeRestController = new EmployeeRestController();
	}

	@Test
	public void testFindAll() {
		List<Employee> allEmployeesFromEndPoint = employeeRestController.findAll();

		assertEquals(allEmployees, allEmployeesFromEndPoint);
	}

	@Test
	public void testFindEmployeeUsingId() {
		int id = 1;
		Employee employeeFromEndPoint = employeeRestController.getEmployee(id);

		assertEquals(employee1, employeeFromEndPoint);
	}

	@Test
	public void testFindEmployeeWithInvalidId() {
		int id = -1;

		assertThrows(RuntimeException.class, () -> employeeRestController.getEmployee(id));
	}

	@Test
	public void testAddEmployee() {
		Employee employee5 = new Employee(5, "Employee5", "Employee5@gmail.com");

		boolean resultt = employeeRestController.addEmployee(employee5);

		assertTrue(resultt);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 1;
		String result = employeeRestController.deleteEmployee(id);

		assertEquals(result, String.format("Deleted employee id - %d", id));
	}

	@Test
	public void testDeleteEmployeeWithInvalidId() {
		int id = -1;
		
		assertThrows(RuntimeException.class, () -> employeeRestController.deleteEmployee(id));
	}

}
