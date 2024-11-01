package pro.san.varyan.VaryanHomeWork.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeAlreadyAddedException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeStorageIsFullException;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private static final Random RANDOM = new Random();

    private final EmployeeService employeeService = new EmployeeService();

    @DisplayName("Положительный тест на добавление сотрудника")
    @Test
    void addEmployeeTest() {
        int sizeBefore = employeeService.findAll().size();
        int expected = ++sizeBefore;
        Employee newEmployee = new Employee("Дмитрий", "Якушин", 2, 78_000);

        employeeService.addEmployee(newEmployee);

        int actual = employeeService.findAll().size();
        assertEquals(expected, actual);

        boolean isAdded = employeeService.findAll()
                .stream()
                .anyMatch(newEmployee::equals);
        assertTrue(isAdded);

    }

    @DisplayName("Негативный тест на превышение количества сотрудников")
    @Test
    void addEmployeeIsFullTest() {
        int max = 20;
        Stream.generate(EmployeeServiceTest::getRandomEmployee)
                .limit(max - 1)
                .forEach(employeeService::addEmployee);

        assertDoesNotThrow(() ->
                employeeService.addEmployee(getRandomEmployee()));
        assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee(getRandomEmployee()));

        int actual = employeeService.findAll().size();
        assertEquals(max, actual);

    }

    @DisplayName("Негативный тест на повторное долбавление сотрудника")
    @Test
    void addEmployeeAlreadyAddedTest() {
        Employee employee = getRandomEmployee();

        assertDoesNotThrow(() ->
                employeeService.addEmployee(employee));
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee(employee));


    }

    private static Employee getRandomEmployee() {
        return new Employee("Петр" + RANDOM.nextInt(),
                "Кашин" + RANDOM.nextInt(),
                3,
                78_000);
    }


}
