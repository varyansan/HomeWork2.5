package pro.san.varyan.VaryanHomeWork.employee;

import java.util.Objects;

public class Employee {

    private String firstName;
    private String lastName;
    private int department;
    private double salary;


    public Employee(String firstName, String lastName) {
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
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
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

