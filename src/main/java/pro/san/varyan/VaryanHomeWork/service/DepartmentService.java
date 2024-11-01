package pro.san.varyan.VaryanHomeWork.service;

import pro.san.varyan.VaryanHomeWork.employee.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {


    List<Employee> getEmployeesByDepartmentId(Integer departmentId);


    long getTotalSalaryByDepartmentId(Integer departmentId);


    long getMaxSalaryByDepartmentId(Integer departmentId);


    long getMinSalaryByDepartmentId(Integer departmentId);


    Map<Integer, List<Employee>> getEmployeesGroupedByDepartment();
}

