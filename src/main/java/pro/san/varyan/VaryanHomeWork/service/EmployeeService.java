package pro.san.varyan.VaryanHomeWork.service;

import org.springframework.stereotype.Service;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeAlreadyAddedException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeNotFoundException;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private final int LIST_SIZE = 10;

    public Employee addEmployee(String firstName, String lastName, int department, double salary) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() >= LIST_SIZE) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников!");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть в списках!");
        }
        employees.put(employee.getFullName(),employee);
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




