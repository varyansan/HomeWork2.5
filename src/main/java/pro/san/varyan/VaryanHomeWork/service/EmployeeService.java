package pro.san.varyan.VaryanHomeWork.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeAlreadyAddedException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeNotFoundException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private final int LIST_SIZE = 20;

    private void listWithEmployee() {
        addEmployee("Роман", "Власов", 2, 89_000);
        addEmployee("Дмитрий", "Якушин", 1, 75_000);
        addEmployee("Дарья", "Терешко", 3, 107_500);
        addEmployee("Рита", "Скиттер", 2, 61_000);
        addEmployee("Лиан", "Моргот", 3, 90_100);
        addEmployee("Игорь", "Крутой", 1, 80_000);
        addEmployee("Тимофей", "Рубинов", 2, 56_000);
        addEmployee("Лариса", "Гузеева", 1, 200_000);
        addEmployee("Джин", "Ким", 3, 150_000);
        addEmployee("Марина", "Волохова", 2, 64_550);
    }

    Employee addEmployee(Employee employee) {
        return addEmployee(employee.getFirstName(),
                employee.getLastName(),
                employee.getDepartment(),
                employee.getSalary());
    }

    public Employee addEmployee(String firstName, String lastName, int department, double salary) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName, department, (int) salary);
        if (employees.size() >= LIST_SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников!");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в списках!");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }


    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Такого сотрудника нет в списках!");
        }
        employees.remove(employee.getFullName());
        return null;
    }

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Такого сотрудника нет в базе данных!");
        }
        employees.containsKey(employee.getFullName());
        return employees.get(employee.getFullName());
    }

    public String getAllEmployee() {
        return employees.toString();
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }


}




