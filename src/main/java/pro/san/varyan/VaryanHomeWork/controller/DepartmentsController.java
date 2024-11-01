package pro.san.varyan.VaryanHomeWork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.san.varyan.VaryanHomeWork.employee.Employee;
import pro.san.varyan.VaryanHomeWork.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable Integer id) {
        return departmentService.getEmployeesByDepartmentId(id);

    }

    @GetMapping("/{id}/salary/sum")
    public Long getTotalSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.getTotalSalaryByDepartmentId(id);

    }

    @GetMapping("/{id}/salary/max")
    public Long getMaxSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.getMaxSalaryByDepartmentId(id);

    }

    @GetMapping("/{id}/salary/min")
    public Long getMinSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.getMinSalaryByDepartmentId(id);

    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployeesGroupedByDepartment() {
        return departmentService.getEmployeesGroupedByDepartment();

    }
}


