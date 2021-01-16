package model;

import java.util.List;
import java.util.Objects;

public class EmployeeDataModel {
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeDataModel)) return false;
        EmployeeDataModel that = (EmployeeDataModel) o;
        return Objects.equals(getEmployees(), that.getEmployees());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployees());
    }

    @Override
    public String toString() {
        return "EmployeeDataModel{" +
                "employees=" + employees +
                '}';
    }
}
