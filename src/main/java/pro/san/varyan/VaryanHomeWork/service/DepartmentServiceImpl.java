package pro.san.varyan.VaryanHomeWork.service;

import org.springframework.stereotype.Service;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getEmployeesByDepartmentId(Integer departmentId) {
        return employeeService.findAll()
                .stream()
                .filter(Objects::nonNull)
                .filter(e -> e.getDepartment() == departmentId)
                .toList();
    }

    @Override
    public long getTotalSalaryByDepartmentId(Integer departmentId) {
        return getEmployeesByDepartmentId(departmentId)
                .stream()
                .mapToInt(e -> (int) e.getSalary())
                .sum();

    }

    @Override
    public long getMaxSalaryByDepartmentId(Integer departmentId) {
        return getEmployeesByDepartmentId(departmentId)
                .stream()
                .mapToInt(e -> (int) e.getSalary())
                .max()
                .orElseThrow(()-> new EmployeeNotFoundException("Not found"));
    }

    @Override
    public long getMinSalaryByDepartmentId(Integer departmentId) {
        return getEmployeesByDepartmentId(departmentId)
                .stream()
                .mapToInt(e -> (int) e.getSalary())
                .min()
                .orElseThrow();
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment
                ));
    }
}
