package pro.san.varyan.VaryanHomeWork.service;

import org.springframework.stereotype.Service;
import pro.san.varyan.VaryanHomeWork.employee.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee getMaxSalaryEmployee(int department){

        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("В списке нет людей!"))
                ;

    }

    public Employee getMinSalaryEmployee(int department) {

        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new RuntimeException("В списке нет людей!"))
                ;
    }

    public List<Employee> getAllDepartmentsId(int department) {

        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList())
                ;
    }

    public Map<Integer, List<Employee>> getAllEmployees() {

        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }
}
