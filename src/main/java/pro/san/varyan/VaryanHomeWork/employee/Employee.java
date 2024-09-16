package pro.san.varyan.VaryanHomeWork.employee;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import pro.san.varyan.VaryanHomeWork.exception.EmployeeNameValidationException;

public class Employee {

    private String firstName;
    private String lastName;
    private int department;
    private double salary;


    public Employee(String firstName, String lastName) {
        this.department = department;
        this.firstName = checkParam(firstName);
        this.lastName = checkParam(lastName);
        this.salary = salary;
    }

    private String checkParam(String string) {
        if (!StringUtils.isAlpha(string)) {
            throw new EmployeeNameValidationException("Неверное имя!");
        }
        return StringUtils.capitalize(string);
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return checkParam(firstName);
    }

    public String getLastName() {
        return checkParam(lastName);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return department == employee.department && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }

    @Override
    public String toString() {
        return "Работник: " +
                "Имя: " + firstName +
                ", фамилия: " + lastName +
                ", отдел: " + department +
                ", зарплата: " + salary;

    }
}

