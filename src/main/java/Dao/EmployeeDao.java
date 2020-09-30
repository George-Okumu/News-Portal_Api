package Dao;

import models.Department;
import models.Employee;

import java.util.List;

public interface EmployeeDao {
    //create
    void add(Employee employee);
    void addEmployeeToDepartment(Employee employee, Department department);

    //read
    List<Employee> getAll();
    List<Department> getAllDepartmentsForAEmployee (int id);

    Employee findById(int id);

    //delete
    void deleteById(int id);
    void clearAll();
}
