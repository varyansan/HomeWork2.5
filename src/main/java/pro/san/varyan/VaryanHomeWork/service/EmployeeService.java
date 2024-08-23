package pro.san.varyan.VaryanHomeWork.service;

import org.springframework.stereotype.Service;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeAlreadyAddedException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeNotFoundException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    List<Employee> employees = new ArrayList<>(List.of(new Employee("Александр", "Петров"), new Employee("Вадим", "Андреев"), new Employee("Светлана", "Сухова"), new Employee("Иван", "Иванов"), new Employee("Мария", "Шишкина"), new Employee("Алексей", "Воробьев"), new Employee("Людмила", "Рощина")));

    private final int LIST_SIZE = 10;

    public Employee addEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= LIST_SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников!");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в списках!");
        }
        employees.add(employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет в списках!");
        }
        employees.remove(employee);
        return null;
    }

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Такого сотрудника нет в базе данных!");
        }
        employees.contains(employee);
        return employee;
    }

    public String getAllEmployee() {
        return employees.toString();
    }
}




