package pro.san.varyan.VaryanHomeWork.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/max-salary")
    public Employee getMaxSalaryEmployee(@RequestParam ("departmentId") int department){
        return departmentService.getMaxSalaryEmployee(department);
    }

    @GetMapping(path = "/min-salary")
    public Employee getMinSalaryEmployee(@RequestParam ("departmentId") int department) {
        return departmentService.getMinSalaryEmployee(department);
    }

    @GetMapping(path = "/all/by-department")
    public List<Employee> getAllDepartmentsId(@RequestParam ("departmentId") int department) {
        return departmentService.getAllDepartmentsId(department);
    }

    @GetMapping(path = "/all")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployees();
    }
}
