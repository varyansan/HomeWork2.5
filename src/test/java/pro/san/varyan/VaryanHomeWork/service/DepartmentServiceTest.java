package pro.san.varyan.VaryanHomeWork.service;

import net.bytebuddy.asm.MemberSubstitution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    private static final Random RANDOM = new Random();

    private static final Collection<Employee> employeeList = List.of(
            new Employee("Дмитрий", "Якушин", 1, 78_000),
            new Employee("Анна", "Смирнова", 2, 105_000),
            new Employee("Сергей", "Иванов", 2, 105_000),
            new Employee("Елена", "Петрова", 3, 92_000),
            new Employee("Александр", "Кузнецов", 3, 80_000),
            new Employee("Мария", "Соколова", 3, 75_000),
            new Employee("Андрей", "Попов", 1, 90_000),
            new Employee("Ольга", "Лебедева", 2, 70_000),
            new Employee("Игорь", "Федоров", 2, 88_000),
            new Employee("Татьяна", "Морозова", 1, 95_000),
            new Employee("Павел", "Зайцев", 2, 55_000),
            new Employee("Наталья", "Коваленко", 2, 72_000),
            new Employee("Владимир", "Никифоров", 3, 60_000),
            new Employee("Светлана", "Сидорова", 1, 67_000),
            new Employee("Максим", "Громов", 1, 81_000),
            new Employee("Ксения", "Смирнова", 3, 74_000),
            new Employee("Руслан", "Белов", 2, 93_000),
            new Employee("Чингиз", "Солодовников", 3, 79_000),
            new Employee("Анастасия", "Тихонова", 1, 66_000),
            new Employee("Ярослав", "Фомина", 2, 82_000)
    );

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @DisplayName("Положительный тест на получение сотрудников по департаменту")
    @Test
    void addEmployeeByDepartmentTest() {
        int requestDepartment = 100;
        Collection<Employee> expected = employeeList.stream()
                .filter(employee -> employee.getDepartment() == requestDepartment)
                .toList();

        when(employeeService.findAll())
                .thenReturn(employeeList);

        Collection<Employee> actual = departmentService.getEmployeesByDepartmentId(requestDepartment);

        verify(employeeService, times(1)).findAll();
        assertEquals(expected, actual);

    }

    @DisplayName("Негативный тест на получение сотрудников из несуществующего отдела")
    @Test
    void addEmployeeByDepartmentNegativeTest() {
        int requestDepartment = 100;

        when(employeeService.findAll())
                .thenReturn(employeeList);

        Collection<Employee> actual = departmentService.getEmployeesByDepartmentId(requestDepartment);

        verify(employeeService, times(1)).findAll();
        assertTrue(actual.isEmpty());

    }

    @DisplayName("Негативный тест на получение сотрудников с пустым хранилищем")
    @Test
    void addEmployeeByDepartmentNegativeTest2() {
        int requestDepartment = 100;

        when(employeeService.findAll())
                .thenReturn(EMPTY_LIST);

        Collection<Employee> actual = departmentService.getEmployeesByDepartmentId(requestDepartment);

        verify(employeeService, times(1)).findAll();
        assertTrue(actual.isEmpty());

    }

    @DisplayName("Положительный тест на получение суммы зарплат по департаменту")
    @ParameterizedTest
    @MethodSource("provideDataForSum")
    void getSalarySumByDepartmentTest(int departmentId, long expected) {

        when(employeeService.findAll())
                .thenReturn(employeeList);

        long actual = departmentService.getTotalSalaryByDepartmentId(departmentId);

        verify(employeeService, times(1)).findAll();
        assertEquals(expected, actual);

    }

    @DisplayName("Положительный тест на получение максимальной зарплаты по департаменту")
    @ParameterizedTest
    @MethodSource("provideDataForMax")
    void getMaxSalaryByDepartmentTest(int departmentId, long expected) {

        when(employeeService.findAll())
                .thenReturn(employeeList);

        long actual = departmentService.getMaxSalaryByDepartmentId(departmentId);

        verify(employeeService, times(1)).findAll();
        assertEquals(expected, actual);

    }

    @DisplayName("Негативный тест на получение максимальной зарплаты по департаменту")
    @Test
    void getMaxSalaryByDepartmentNegativeTest() {

        int requestDepartmentID = 10;
        when(employeeService.findAll())
                .thenReturn(employeeList);


        assertThrows(EmployeeNotFoundException.class, () ->
                departmentService.getMaxSalaryByDepartmentId(requestDepartmentID));
        verify(employeeService, times(1)).findAll();

    }

    private static Stream<Arguments> provideDataForSum() {
        return Stream.of(
                Arguments.arguments(1, 477_000),
                Arguments.arguments(100_000, 0),
                Arguments.arguments(2, 670_000)
        );
    }

    private static Stream<Arguments> provideDataForMax() {
        return Stream.of(
                Arguments.arguments(1, 95_000),
                Arguments.arguments(3, 92_000),
                Arguments.arguments(2, 105_000)
        );
    }
}


